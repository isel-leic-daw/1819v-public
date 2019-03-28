package isel.leic.daw.services.hvac

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

const val MINIMUM_TEMPERATURE = 16
const val MAXIMUM_TEMPERATURE = 32

@Service
class Control(private val cooler: Cooler,
              private val heater: Heater,
              private val sensor: Sensor,
              override var versionNumber: Int = 0) : HVAC {

    @Synchronized
    private fun incrementVersionNumber() {
        versionNumber += 1;
    }

    private val logger = LoggerFactory.getLogger(Control::class.java)

    private fun hasTargetBeenReached() = currentTemperature == desiredTemperature

    private fun turnOff() {
        heater.turnOff()
        cooler.turnOff()
        sensor.temperatureListener = null
    }

    private fun doControl() {
        if (hasTargetBeenReached()) {
            turnOff()
            return
        }

        sensor.temperatureListener = {
            logger.info("Temperature has changed to ${it.temperature}")
            incrementVersionNumber()
            if (hasTargetBeenReached()) {
                turnOff()
            }
        }

        if (desiredTemperature > currentTemperature) {
            heater.turnOn()
            cooler.turnOff()
        }
        else {
            heater.turnOff()
            cooler.turnOn()
        }
    }

    override var desiredTemperature: Int = 22
        @Synchronized set(value) {
            if (value < MINIMUM_TEMPERATURE || value > MAXIMUM_TEMPERATURE)
                throw InvalidTemperature("The specified temperature $value is not in the accepted interval " +
                        "[$MINIMUM_TEMPERATURE..$MAXIMUM_TEMPERATURE]")

            field = value
            if (enabled) doControl()
            incrementVersionNumber()
        }
        @Synchronized get() = field

    override val currentTemperature: Int
        get() = sensor.temperature

    override var enabled: Boolean = false
        @Synchronized set(value) {
            field = value
            if (value) doControl()
            else turnOff()
        }
        @Synchronized get() = field
}

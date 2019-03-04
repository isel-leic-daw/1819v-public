package isel.leic.daw.hvac

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Control(private val cooler: Cooler,
              private val heater: Heater,
              private val sensor: Sensor) : HVAC {

    private val logger = LoggerFactory.getLogger(Control::class.java)

    init {
        logger.info("sensor hash ${sensor.hashCode()}")
    }

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
        set(value) {
            field = value
            if (enabled) doControl()
        }

    override val currentTemperature: Int
        get() = sensor.temperature

    override var enabled: Boolean = false
        set(value) {
            field = value
            if (value) doControl()
            else turnOff()
        }
}

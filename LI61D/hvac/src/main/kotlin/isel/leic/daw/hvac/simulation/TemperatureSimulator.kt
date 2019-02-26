package isel.leic.daw.hvac.simulation

import isel.leic.daw.hvac.Cooler
import isel.leic.daw.hvac.Heater
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.concurrent.fixedRateTimer

private const val FIVE_SECS: Long = 5000

private class TemperatureActuator(val message: String?, val action: TimerTask.() -> Unit) {

    private var timer: Timer? = null
    val isOn get() = timer != null

    fun turnOn() {
        if (isOn) return
        timer = fixedRateTimer(message, false, FIVE_SECS, FIVE_SECS, action)
    }

    fun turnOff() {
        if (isOn) {
            timer?.cancel()
            timer = null
        }
    }
}

class HeaterSimulator(sensor: SensorSimulator) : Heater {

    private val logger = LoggerFactory.getLogger(HeaterSimulator::class.java)

    private val actuator = TemperatureActuator("Heater running") {
        logger.info("Heater is running and current temperature is ${sensor.temperature}")
        sensor.temperature += 1
    }

    override val isOn: Boolean = actuator.isOn

    override fun turnOn() {
        logger.info("Turning Heater ON")
        return actuator.turnOn()
    }

    override fun turnOff() {
        logger.info("Turning Heater OFF")
        return actuator.turnOff()
    }
}

class CoolerSimulator(sensor: SensorSimulator) : Cooler {

    private val logger = LoggerFactory.getLogger(CoolerSimulator::class.java)

    private val actuator = TemperatureActuator("Cooler running") {
        println("Cooler is running and current temperature is ${sensor.temperature}")
        sensor.temperature -= 1
    }

    override val isOn: Boolean = actuator.isOn

    override fun turnOn() {
        logger.info("Turning Cooler ON")
        return actuator.turnOn()
    }

    override fun turnOff() {
        logger.info("Turning Cooler OFF")
        return actuator.turnOff()
    }
}
package isel.leic.daw.hvac.simulation

import isel.leic.daw.hvac.Sensor
import org.springframework.stereotype.Component

@Component
class SensorSimulator(initialTemperature: Int = 20) : Sensor {

    override var temperatureListener: ((source: Sensor) -> Unit)? = null
    override var temperature: Int = initialTemperature
        set(value) {
            if (value != field) {
                field = value
                temperatureListener?.invoke(this)
            }
        }
}
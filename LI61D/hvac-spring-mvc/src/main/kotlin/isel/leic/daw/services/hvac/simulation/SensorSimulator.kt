package isel.leic.daw.services.hvac.simulation

import isel.leic.daw.services.hvac.Sensor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SensorSimulator(@Value("\${hvac.comfort.temperature}") initialTemperature: Int) : Sensor {

    override var temperatureListener: ((source: Sensor) -> Unit)? = null

    @Volatile
    override var temperature: Int = initialTemperature
        set(value) {
            if (value != field) {
                field = value
                temperatureListener?.invoke(this)
            }
        }
}
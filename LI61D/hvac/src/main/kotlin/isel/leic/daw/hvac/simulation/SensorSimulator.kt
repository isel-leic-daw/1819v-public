package isel.leic.daw.hvac.simulation

import isel.leic.daw.hvac.Sensor

class SensorSimulator(initialTemperature: Int) : Sensor {

    override var temperatureListener: ((source: Sensor) -> Unit)? = null
    override var temperature: Int = initialTemperature
        set(value) {
            if (value != field) {
                field = value
                temperatureListener?.invoke(this)
            }
        }
}
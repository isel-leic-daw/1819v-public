package isel.leic.daw.hvac

interface Sensor {
    val temperature: Int
    var temperatureListener: ((source: Sensor) -> Unit)?
}

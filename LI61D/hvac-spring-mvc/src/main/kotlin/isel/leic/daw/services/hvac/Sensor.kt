package isel.leic.daw.services.hvac

interface Sensor {
    val temperature: Int
    var temperatureListener: ((source: Sensor) -> Unit)?
}

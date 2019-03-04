package isel.leic.daw.hvac


interface HVAC {
    var desiredTemperature: Int
    val currentTemperature: Int
    var enabled: Boolean
}

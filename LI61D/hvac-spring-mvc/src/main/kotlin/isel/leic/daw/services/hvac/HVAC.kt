package isel.leic.daw.services.hvac


interface HVAC {
    var desiredTemperature: Int
    val currentTemperature: Int
    var enabled: Boolean
}

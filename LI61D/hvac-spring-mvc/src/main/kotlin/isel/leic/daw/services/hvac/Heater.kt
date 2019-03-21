package isel.leic.daw.services.hvac

interface Heater {
    fun turnOn()
    fun turnOff()
    val isOn: Boolean
}
package isel.leic.daw.hvac

interface Heater {
    fun turnOn()
    fun turnOff()
    val isOn: Boolean
}
package isel.leic.daw.services.hvac

interface Cooler {
    val isOn: Boolean
    fun turnOn()
    fun turnOff()
}
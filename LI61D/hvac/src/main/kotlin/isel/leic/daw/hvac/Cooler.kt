package isel.leic.daw.hvac

interface Cooler {
    val isOn: Boolean
    fun turnOn()
    fun turnOff()
}
package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hvac.HVAC
import org.springframework.web.bind.annotation.*

data class TemperatureStatus @JsonCreator constructor(val current: Int, val desired: Int)
data class TemperatureValue @JsonCreator constructor(val value: Int)

@RestController
@RequestMapping(value = ["/temperature"])
class TemperatureController(private val hvac: HVAC) {

    @GetMapping(path = [""])
    fun getTemperatureStatus() = TemperatureStatus(
            hvac.currentTemperature, hvac.desiredTemperature)

    @GetMapping(path = ["/current"])
    fun getCurrentTemperature() = TemperatureValue(hvac.currentTemperature)

    @GetMapping(path = ["/target"])
    fun getDesiredTemperature() = TemperatureValue(hvac.desiredTemperature)

    @PutMapping(path = ["/target"])
    fun setDesiredTemperature(@RequestBody temperature: TemperatureValue): TemperatureStatus {
        hvac.desiredTemperature = temperature.value
        return TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)
    }
}
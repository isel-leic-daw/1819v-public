package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hvac.HVAC
import isel.leic.daw.hvac.InvalidTemperature
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

data class TemperatureStatus @JsonCreator constructor(val current: Int, val desired: Int)
data class TemperatureValue @JsonCreator constructor(val value: Int)

@RestController
@RequestMapping(value = ["/temperature"])
class TemperatureController(private val hvac: HVAC) {

    @GetMapping
    fun getTemperatureStatus() = TemperatureStatus(
            hvac.currentTemperature, hvac.desiredTemperature)

    @GetMapping(path = ["/current"])
    fun getCurrentTemperature() = TemperatureValue(hvac.currentTemperature)

    @GetMapping(path = ["/target"])
    fun getDesiredTemperature() = TemperatureValue(hvac.desiredTemperature)

    @PutMapping(path = ["/target"])
    fun setDesiredTemperature(@RequestBody temperature: TemperatureValue): TemperatureStatus {
        try {
            hvac.desiredTemperature = temperature.value
            return TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)
        }
        catch (e: InvalidTemperature) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, null, e)
        }
    }
}
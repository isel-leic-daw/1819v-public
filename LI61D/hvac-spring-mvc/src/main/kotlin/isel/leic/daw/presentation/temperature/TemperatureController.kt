package isel.leic.daw.presentation.temperature

import isel.leic.daw.presentation.hvac.HVAC_URI
import isel.leic.daw.services.hvac.HVAC
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = [TEMPERATURE_URI], produces = ["application/hal+json", "application/json"])
class TemperatureController(private val hvac: HVAC) {

    @GetMapping
    fun getTemperatureStatus() = TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)

    @GetMapping(path = [CURRENT_ROUTE])
    fun getCurrentTemperature(req: HttpServletRequest) : TemperatureResult {
        return TemperatureResult(hvac.currentTemperature, CURRENT_URI, HVAC_URI)
    }

    @GetMapping(path = [TARGET_ROUTE])
    fun getDesiredTemperature() = TemperatureResult(hvac.desiredTemperature, TARGET_URI, HVAC_URI)

    @PutMapping(path = [TARGET_ROUTE])
    fun setDesiredTemperature(@RequestBody temperature: TemperatureValue): TemperatureStatus {
        hvac.desiredTemperature = temperature.value
        return TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)
    }
}
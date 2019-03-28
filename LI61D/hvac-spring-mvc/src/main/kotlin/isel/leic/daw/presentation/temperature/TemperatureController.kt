package isel.leic.daw.presentation.temperature

import isel.leic.daw.presentation.AuthorizationRequired
import isel.leic.daw.presentation.hateoas.ProblemJson
import isel.leic.daw.presentation.hvac.HVAC_URI
import isel.leic.daw.services.hvac.HVAC
import isel.leic.daw.services.hvac.InvalidTemperature
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = [TEMPERATURE_URI], produces = ["application/hal+json", "application/json"])
class TemperatureController(private val hvac: HVAC) {

    @ExceptionHandler
    fun handleInvalidTemperature(e: InvalidTemperature): ResponseEntity<ProblemJson> {
        return ResponseEntity
                .badRequest()
                .body(ProblemJson(
                        "http://docs.hvac.api/errors/invalidTemperature",
                        "Specified temperature is not within acceptable bounds",
                        e.message.orEmpty(),
                        HttpStatus.BAD_REQUEST.value()
                ))
    }

    @GetMapping
    fun getTemperatureStatus(): ResponseEntity<TemperatureStatus> {
        return ResponseEntity
                .ok()
                .eTag(hvac.versionNumber.toString())
                .body(TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature))
    }

    @GetMapping(path = [CURRENT_ROUTE])
    fun getCurrentTemperature(req: HttpServletRequest) : TemperatureResult {
        return TemperatureResult(hvac.currentTemperature, CURRENT_URI, HVAC_URI)
    }

    @GetMapping(path = [TARGET_ROUTE])
    fun getDesiredTemperature() = TemperatureResult(hvac.desiredTemperature, TARGET_URI, HVAC_URI)

    @PutMapping(path = [TARGET_ROUTE])
    @AuthorizationRequired
    fun setDesiredTemperature(@RequestBody temperature: TemperatureValue): TemperatureStatus {
        hvac.desiredTemperature = temperature.value
        return TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)
    }
}
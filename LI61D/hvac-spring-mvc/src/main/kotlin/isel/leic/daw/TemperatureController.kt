package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hateoas.HalObject
import isel.leic.daw.hateoas.Link
import isel.leic.daw.hvac.HVAC
import isel.leic.daw.hvac.InvalidTemperature
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest

private const val PARENT_ROUTE = "/temperature"
private const val TARGET_ROUTE = "/target"
private const val CURRENT_ROUTE = "/current"

private const val PARENT_URI = PARENT_ROUTE
private const val TARGET_URI = "$PARENT_ROUTE$TARGET_ROUTE"
private const val CURRENT_URI = "$PARENT_ROUTE$CURRENT_ROUTE"

data class TemperatureStatus @JsonCreator constructor(val current: Int, val desired: Int) : HalObject(
    mapOf("self" to Link(PARENT_ROUTE), "current" to Link(CURRENT_URI), "desired" to Link(TARGET_URI))
)

data class TemperatureValue @JsonCreator constructor(val value: Int)

data class TemperatureResult(val value: Int, private val self: String, private val parent: String) : HalObject(
    mapOf("self" to Link(self), "parent" to Link(parent))
)

@RestController
@RequestMapping(value = [PARENT_ROUTE], produces = ["application/hal+json", "application/json"])
class TemperatureController(private val hvac: HVAC) {

    @GetMapping
    fun getTemperatureStatus() = TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)

    @GetMapping(path = [CURRENT_ROUTE])
    fun getCurrentTemperature(req: HttpServletRequest) : TemperatureResult {
        return TemperatureResult(hvac.currentTemperature, CURRENT_URI, PARENT_URI)
    }

    @GetMapping(path = [TARGET_ROUTE])
    fun getDesiredTemperature() = TemperatureResult(hvac.desiredTemperature, TARGET_URI, PARENT_URI)

    @PutMapping(path = [TARGET_ROUTE])
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
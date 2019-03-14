package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hateoas.HalObject
import isel.leic.daw.hateoas.Link
import isel.leic.daw.hvac.HVAC
import org.springframework.web.bind.annotation.*

private const val PARENT_ROUTE = "/hvac"
private const val ENABLED_ROUTE = "/enabled"

private const val PARENT_URI = PARENT_ROUTE
private const val ENABLED_URI = "$PARENT_ROUTE$ENABLED_ROUTE"

data class HvacState @JsonCreator constructor(val enabled: Boolean) : HalObject(
    mapOf("self" to Link(PARENT_URI), "enabled" to Link(ENABLED_URI))
)

data class HvacEnabledValue @JsonCreator constructor(val value: Boolean) : HalObject(
    mapOf("self" to Link(ENABLED_URI), "parent" to Link(PARENT_URI))
)

@RestController
@RequestMapping(value= [PARENT_ROUTE], produces = ["application/hal+json", "application/json"])
class HvacController(private val hvac: HVAC) {

    @GetMapping
    fun getHvacState() = HvacState(hvac.enabled)

    @GetMapping(path = [ENABLED_ROUTE])
    fun getHvacEnabledState() = HvacEnabledValue(hvac.enabled)

    @PutMapping(path = [ENABLED_ROUTE])
    fun setHvacEnabledState(@RequestBody value: HvacEnabledValue): HvacState {
        hvac.enabled = value.value
        return HvacState(hvac.enabled)
    }
}
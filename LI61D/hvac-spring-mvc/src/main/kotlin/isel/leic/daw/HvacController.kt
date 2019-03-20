package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hateoas.HalObject
import isel.leic.daw.hateoas.Link
import isel.leic.daw.hvac.HVAC
import org.springframework.web.bind.annotation.*

private const val HVAC_BASE_URI = "/hvac"
private const val ENABLED_ROUTE = "/enabled"

const val HVAC_URI = HVAC_BASE_URI
const val HVAC_ENABLED_URI = "$HVAC_BASE_URI$ENABLED_ROUTE"

data class HvacState @JsonCreator constructor(val enabled: Boolean) : HalObject(
    mapOf(
        "self" to Link(HVAC_URI, "Reload"),
        "enabled" to Link(HVAC_ENABLED_URI, "HVAC enabled state")
    )
)

data class HvacEnabledValue @JsonCreator constructor(val value: Boolean) : HalObject(
    mapOf(
        "self" to Link(HVAC_ENABLED_URI, "Reload"),
        "parent" to Link(HVAC_URI, "HVAC state")
    )
)

@RestController
@RequestMapping(value = [HVAC_BASE_URI], produces = ["application/hal+json", "application/json"])
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
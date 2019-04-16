package isel.leic.daw.presentation.hvac

import io.swagger.annotations.ApiOperation
import isel.leic.daw.presentation.AuthorizationRequired
import isel.leic.daw.services.hvac.HVAC
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = [HVAC_BASE_URI], produces = ["application/hal+json", "application/json"])
/**
 * Actions for accessing resources related to the HVAC state.
 */
class HvacStateActions(private val hvac: HVAC) {

    @ApiOperation(value = "Gets the current state of the HVAC", notes = "The result is the HVAC aggregate state")
    @GetMapping
    fun getHvacState() = HvacState(hvac.enabled)

    @GetMapping(path = [ENABLED_ROUTE])
    fun getHvacEnabledState() = HvacEnabledResult(hvac.enabled)

    @PutMapping(path = [ENABLED_ROUTE])
    @AuthorizationRequired
    fun setHvacEnabledState(@RequestBody value: HvacEnabledValue): HvacState {
        hvac.enabled = value.value
        return HvacState(hvac.enabled)
    }
}
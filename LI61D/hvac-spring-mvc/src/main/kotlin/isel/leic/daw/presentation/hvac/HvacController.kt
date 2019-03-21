package isel.leic.daw.presentation.hvac

import isel.leic.daw.services.hvac.HVAC
import org.springframework.web.bind.annotation.*

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
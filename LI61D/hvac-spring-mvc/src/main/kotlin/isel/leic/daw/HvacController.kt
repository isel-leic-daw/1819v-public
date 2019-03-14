package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hvac.HVAC
import org.springframework.web.bind.annotation.*

data class HvacState @JsonCreator constructor(val enabled: Boolean)
data class HvacEnabledValue @JsonCreator constructor(val value: Boolean)

@RestController
@RequestMapping(value= ["/hvac"])
class HvacController(private val hvac: HVAC) {

    @GetMapping
    fun getHvacState() = HvacState(hvac.enabled)

    @GetMapping(path = ["/enabled"])
    fun getHvacEnabledState() = HvacEnabledValue(hvac.enabled)

    @PutMapping(path = ["/enabled"])
    fun setHvacEnabledState(@RequestBody value: HvacEnabledValue): HvacState {
        hvac.enabled = value.value
        return HvacState(hvac.enabled)
    }
}
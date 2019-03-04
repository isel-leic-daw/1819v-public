package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hvac.HVAC
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

data class HvacState @JsonCreator constructor(val enabled: Boolean)

@RestController
@RequestMapping(value= ["/hvac"])
class HvacController(private val hvac: HVAC) {

    private val logger = LoggerFactory.getLogger(HvacController::class.java)

    init {
        logger.info("Injected HVAC hash is ${hvac.hashCode()}")
    }

    @GetMapping(path = [""])
    fun getHvacState() = HvacState(hvac.enabled)

    @GetMapping(path = ["/enabled"])
    fun getHvacEnabledState() = hvac.enabled

    @PutMapping(path = ["/enabled"])
    fun setHvacEnabledState(@RequestBody value: Boolean): HvacState {
        hvac.enabled = value
        return HvacState(hvac.enabled)
    }
}
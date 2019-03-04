package isel.leic.daw

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.hvac.HVAC
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

data class TemperatureStatus @JsonCreator constructor(val current: Int, val desired: Int)

@RestController
@RequestMapping(value = ["/temperature"])
class TemperatureController(private val hvac: HVAC) {

    private val logger = LoggerFactory.getLogger(TemperatureController::class.java)

    init {
        logger.info("Injected HVAC hash is ${hvac.hashCode()}")
    }

    @GetMapping(path = [""])
    fun getTemperatureStatus() = TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)

    @GetMapping(path = ["/current"])
    fun getCurrentTemperature(): Int = hvac.currentTemperature

    @GetMapping(path = ["/target"])
    fun getDesiredTemperature() = hvac.desiredTemperature

    @PutMapping(path = ["/target"])
    fun setDesiredTemperature(@RequestBody value: Int): TemperatureStatus {
        hvac.desiredTemperature = value
        return TemperatureStatus(hvac.currentTemperature, hvac.desiredTemperature)
    }
}
package isel.leic.daw.hvac

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class InvalidTemperature : IllegalArgumentException("Target temperature is not within acceptable bounds")
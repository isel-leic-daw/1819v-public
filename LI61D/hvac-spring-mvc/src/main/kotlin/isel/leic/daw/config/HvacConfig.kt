package isel.leic.daw.config

import isel.leic.daw.presentation.AuthorizationException
import isel.leic.daw.presentation.hateoas.ProblemJson
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ControllerAdvice
@EnableWebMvc
class HvacConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthorizationInterceptor())
    }

    /**
     * Globally applicable exception handler
     */
    @ExceptionHandler
    fun handleInvalidTemperature(e: AuthorizationException): ResponseEntity<ProblemJson> {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ProblemJson(
                        "http://docs.hvac.api/errors/authorizationRequired",
                        "Authorization required",
                        "Access was denied because the required authorization was not granted",
                        HttpStatus.UNAUTHORIZED.value()
                ))
    }
}

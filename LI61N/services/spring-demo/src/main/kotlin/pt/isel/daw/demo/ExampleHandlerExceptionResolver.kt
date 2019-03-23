package pt.isel.daw.demo

import org.springframework.beans.TypeMismatchException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

data class ErrorModel(val status: Int, val message: String)

@RestControllerAdvice
class ExampleHandlerExceptionResolver : ResponseEntityExceptionHandler() {


    override fun handleTypeMismatch(
            ex: TypeMismatchException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val error = ErrorModel(status = 400, message = "error converting to ${ex.requiredType}")

        return ResponseEntity.badRequest()
                .header("Content-Type","application/problem+json")
                .body(error)
    }

    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception, request: WebRequest): ResponseEntity<ErrorModel> {
        val error = ErrorModel(status = 500, message = "the-message")

        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
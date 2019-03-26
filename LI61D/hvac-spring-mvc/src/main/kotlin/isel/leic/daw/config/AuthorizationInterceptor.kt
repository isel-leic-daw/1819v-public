package isel.leic.daw.config

import isel.leic.daw.presentation.AuthorizationException
import isel.leic.daw.presentation.AuthorizationRequired
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if(handler is HandlerMethod && handler.hasMethodAnnotation(AuthorizationRequired::class.java)) {
            val authHeader = request.getHeader("Authorization")?.split(" ")
            if (authHeader?.get(0) != "Basic" && authHeader?.get(1) != "cGF1bG86YSBtaW5oYSBmcmFzZSBzZWNyZXRh") {
                throw AuthorizationException()
            }
        }
        return true
    }
}
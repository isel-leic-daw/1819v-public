package isel.leic.daw.config

import isel.leic.daw.presentation.AuthorizationRequired
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class AuthorizationInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if(handler is HandlerMethod && handler.method.isAnnotationPresent(AuthorizationRequired::class.java)) {
            val authHeader = request.getHeader("Authorization")?.split(" ")
            if (authHeader?.get(0) != "Basic" && authHeader?.get(1) != "cGF1bG86YSBtaW5oYSBmcmFzZSBzZWNyZXRh") {
                // TODO: Response should be JSON-Problem
                response.sendError(SC_UNAUTHORIZED)
                return false
            }
        }
        return true
    }
}
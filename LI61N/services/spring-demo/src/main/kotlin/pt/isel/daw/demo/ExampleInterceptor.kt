package pt.isel.daw.demo

import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


private val log = loggerFor<ExampleController>()

class ExampleInterceptor : HandlerInterceptor {

    override fun preHandle(
            request: HttpServletRequest,
            response: HttpServletResponse,
            handler: Any): Boolean {

        log.info("Before calling $handler (${handler.javaClass.name})")
        return true
    }

    override fun postHandle(
            request: HttpServletRequest,
            response: HttpServletResponse,

            handler: Any, modelAndView: ModelAndView?) {
        log.info("After calling $handler")
    }
}
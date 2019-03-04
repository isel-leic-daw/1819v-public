package isel.leic.daw

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(1)
class RequestLoggerFilter : Filter {

    private val logger = LoggerFactory.getLogger(RequestLoggerFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        logger.info("Received request ${httpRequest.method} ${httpRequest.requestURI}")
        chain?.doFilter(request, response)
        logger.info("Resulting status code is ${httpResponse.status}")
    }
}
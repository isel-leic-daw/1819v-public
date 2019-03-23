package pt.isel.daw.demo

import org.springframework.stereotype.Component

@Component
class Service1Impl(val svc2: Service2) : Service1 {
    override fun getGreeting() = svc2.convert("Hello from Service1Impl")
}
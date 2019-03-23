package pt.isel.daw.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// A container for handlers
@RestController
@RequestMapping("/example")
class ExampleController(val svc1: Service1) {

    // An handler is a method
    @GetMapping("1")
    fun get() = "Hello World"

    @GetMapping("2")
    fun get2() = svc1.getGreeting()

    @GetMapping("3/{i}")
    fun get3(
            @PathVariable("i") i: Int) = "Hello $i"
}
package pt.isel.daw.demo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

private val log = loggerFor<ExampleController>()

@RestController
@RequestMapping("/example")
class ExampleController(val exampleService: ExampleService) {

    init {
        log.info("instance was constructed")
    }

    @GetMapping()
    fun get() = exampleService.greetings()

    @GetMapping("1/{id}")
    fun get1(@PathVariable("id") id: Int) = "Request with id=$id"

    @GetMapping("2")
    fun get2(@RequestParam("id") id: Int) = "Request with id=$id"

    @RequestMapping(path = ["3"], produces = ["text/plain", "text/html"])
    fun get3(@RequestParam("id") id: Int?) = "Request with id=${id?.toString() ?: "<na>"}"

    @GetMapping("4")
    fun get4(@RequestParam prms: MultiValueMap<String, String>) = prms
            .map { "${it.key}: ${it.value.joinToString(", ", "[", "]")}\n" }
            .joinToString()

    data class OutputModel5(
            val theInt: Int,
            val theString: String)

    @GetMapping("5")
    fun get5() = OutputModel5(42, "hello")

    @JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
    data class OutputModel6(
            val theInt: Int,
            val theString: String)

    @GetMapping("6")
    fun get6() = OutputModel6(42, "hello")

    data class OutputModel7(
            @JsonProperty("the-int")
            val theInt: Int,
            val theString: String)

    @GetMapping("7")
    fun get7() = OutputModel7(42, "hello")

    @GetMapping("8")
    fun get8(clientIp: ClientIp) = "Hello ${clientIp.ipString}"

    @GetMapping("9")
    fun get9() = QrCode("https://www.example.com")

    @GetMapping("10")
    fun get10() = ResponseEntity.status(400)
            .header("My-Header", "My-Value1", "My-Value2")
            .body(OutputModel5(42, "hello"))

    @GetMapping("11")
    fun get11(): Nothing = throw Exception("the-error")

}

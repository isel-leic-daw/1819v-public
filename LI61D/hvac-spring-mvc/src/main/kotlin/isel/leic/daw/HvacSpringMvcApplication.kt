package isel.leic.daw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HvacSpringMvcApplication

fun main(args: Array<String>) {
	runApplication<HvacSpringMvcApplication>(*args)
}

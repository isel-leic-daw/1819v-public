package pt.isel.daw.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoApplication {

    //@Bean
    fun service2(): Service2 = object: Service2 {
        override fun convert(str: String) = str.toLowerCase()
    }
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

package pt.isel.daw.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExampleConfiguration {

    @Bean
    fun service2(): Service2 = object: Service2 {
        override fun convert(str: String) = str.toLowerCase()
    }
}
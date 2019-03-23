package pt.isel.daw.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoApplication {
	@Bean
	fun exampleService(anotherService: AnotherService) : ExampleService = DefaultExampleService(anotherService)

    @Bean
    fun anotherService() = DefaultAnotherService()
}

fun main(args: Array<String>) {
	val app = runApplication<DemoApplication>(*args)
	//app.beanDefinitionNames.forEach{ println(it)}
	println(app.beanFactory.getBeanDefinition("exampleService"))
}

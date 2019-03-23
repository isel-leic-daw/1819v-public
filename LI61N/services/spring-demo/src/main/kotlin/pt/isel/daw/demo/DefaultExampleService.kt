package pt.isel.daw.demo

class DefaultExampleService(val svc : AnotherService) : ExampleService {
    override fun greetings() = "Hello World, from DefaultExampleService, at ${svc.now()}"
}
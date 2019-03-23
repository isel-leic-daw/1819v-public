package pt.isel.daw.demo

import java.time.Instant

class DefaultAnotherService : AnotherService {
    override fun now() = Instant.now()
}

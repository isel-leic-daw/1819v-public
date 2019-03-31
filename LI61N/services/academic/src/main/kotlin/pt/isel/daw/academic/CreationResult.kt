package pt.isel.daw.academic

sealed class CreationResult
data class Created(val id: String) : CreationResult()
class BadValues() : CreationResult()
class AlreadyExists() : CreationResult()
class NotAllowed() : CreationResult()


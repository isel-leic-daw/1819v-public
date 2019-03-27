package isel.leic.daw.presentation

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
/**
 * Annotation used to signal that a given controller action requires authentication
 */
annotation class AuthorizationRequired

/**
 * Exception thrown whenever an access to a resource requires authorization and the required credentials have not
 * been presented.
 */
class AuthorizationException : Exception()
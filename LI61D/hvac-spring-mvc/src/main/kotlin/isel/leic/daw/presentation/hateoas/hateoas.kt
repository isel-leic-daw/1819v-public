package isel.leic.daw.presentation.hateoas

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Class whose instances represent links, as described in <a href="https://tools.ietf.org/html/draft-kelly-json-hal-08">
 * JSON Hypertext Application Language</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Link @JsonCreator constructor(
        val href: String,
        val title: String? = null,
        val templated: Boolean? = null)

/**
 * Abstract class to be used as a base class for HAL representations.
 */
abstract class HalObject(val _links: Map<String, Link>)


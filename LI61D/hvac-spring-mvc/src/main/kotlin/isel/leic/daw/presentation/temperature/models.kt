package isel.leic.daw.presentation.temperature

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.presentation.hateoas.HalObject
import isel.leic.daw.presentation.hateoas.Link

/**
 * Output model for aggregate temperature status.
 *
 * @property current    The current temperature
 * @property desired    The target temperature
 */
data class TemperatureStatus @JsonCreator constructor(val current: Int, val desired: Int) : HalObject(
    mapOf(
        "self" to Link(TEMPERATURE_URI, "Reload"),
        "http://docs.hvac.api/rels/temperature/current" to Link(CURRENT_URI, "Current temperature"),
        "http://docs.hvac.api/rels/temperature/desired" to Link(TARGET_URI, "Desired temperature")
    )
)

/**
 * Output model for temperature values, expressed in Celsius degrees.
 *
 * @property value  The temperature value
 */
data class TemperatureResult(val value: Int, private val self: String, private val parent: String) : HalObject(
    mapOf("self" to Link(self), "http://docs.hvac.api/rels/temperature" to Link(parent))
)

/**
 * Input model for temperature values, expressed in Celsius degrees.
 */
data class TemperatureValue @JsonCreator constructor(val value: Int)

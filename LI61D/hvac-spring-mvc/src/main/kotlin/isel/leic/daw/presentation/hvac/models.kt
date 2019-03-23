package isel.leic.daw.presentation.hvac

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.presentation.hateoas.HalObject
import isel.leic.daw.presentation.hateoas.Link
import isel.leic.daw.presentation.temperature.TEMPERATURE_URI

/**
 * Output model for the HVAC aggregate state.
 */
data class HvacState @JsonCreator constructor(val enabled: Boolean) : HalObject(
    mapOf(
        "self" to Link(HVAC_URI, "Reload"),
        "http://docs.hvac.api/rels/hvac/enabled" to Link(HVAC_ENABLED_URI, "HVAC enabled state"),
        "http://docs.hvac.api/rels/temperature" to Link(TEMPERATURE_URI, "Temperature settings")
    )
)

/**
 * Output model for the HVAC enabled state.
 */
data class HvacEnabledResult @JsonCreator constructor(val value: Boolean) : HalObject(
    mapOf(
        "self" to Link(HVAC_ENABLED_URI, "Reload"),
        "http://docs.hvac.api/rels/hvac" to Link(HVAC_URI, "HVAC state")
    )
)

/**
 * Input model for the HVAC enabled state.
 */
data class HvacEnabledValue  @JsonCreator constructor(val value: Boolean)
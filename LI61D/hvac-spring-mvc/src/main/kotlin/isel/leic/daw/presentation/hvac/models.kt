package isel.leic.daw.presentation.hvac

import com.fasterxml.jackson.annotation.JsonCreator
import isel.leic.daw.presentation.hateoas.HalObject
import isel.leic.daw.presentation.hateoas.Link

/**
 * Output model for the HVAC aggregate state.
 */
data class HvacState @JsonCreator constructor(val enabled: Boolean) : HalObject(
        mapOf(
                "self" to Link(HVAC_URI, "Reload"),
                "enabled" to Link(HVAC_ENABLED_URI, "HVAC enabled state")
        )
)

/**
 * Output model for the HVAC enabled state.
 */
data class HvacEnabledValue @JsonCreator constructor(val value: Boolean) : HalObject(
        mapOf(
                "self" to Link(HVAC_ENABLED_URI, "Reload"),
                "parent" to Link(HVAC_URI, "HVAC state")
        )
)


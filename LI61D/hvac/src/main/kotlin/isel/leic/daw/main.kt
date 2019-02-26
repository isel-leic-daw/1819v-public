package isel.leic.daw

import isel.leic.daw.hvac.Control
import isel.leic.daw.hvac.HVAC
import isel.leic.daw.hvac.simulation.CoolerSimulator
import isel.leic.daw.hvac.simulation.HeaterSimulator
import isel.leic.daw.hvac.simulation.SensorSimulator

const val COMFORT_TEMPERATURE = 22

typealias Command = (String?) -> Boolean
typealias ParameterizedCommand = () -> Boolean
typealias Route = (String) -> ParameterizedCommand

fun wireItUp(): HVAC {
    val sensor = SensorSimulator(COMFORT_TEMPERATURE)
    return Control(
        CoolerSimulator(sensor),
        HeaterSimulator(sensor),
        sensor)
}

fun initRouting(hvacControl: HVAC): Route {
    val mappings: Map<Pair<String, String?>, Command> = mapOf(
        Pair("EXIT", null) to { _ -> true },
        Pair("GET", "/hvac/enabled") to { _ ->
            println("HVAC is ${if (hvacControl.enabled) "ENABLED" else "DISABLED" }")
            false
        },
        Pair("PUT", "/hvac/enabled") to { params ->
            val enabled = params?.toBoolean() ?: false
            println("HVAC is being ${if (enabled) "ENABLED" else "DISABLED" }")
            hvacControl.enabled = enabled
            false
        },
        Pair("GET", "/temperature/current") to { _ ->
            println("Current temperature is ${hvacControl.currentTemperature}")
            false
        },
        Pair("GET", "/temperature/target") to { _ ->
            println("Target temperature is ${hvacControl.desiredTemperature}")
            false
        },
        Pair("PUT", "/temperature/target") to { params ->
            val newTarget = params?.toInt() ?: COMFORT_TEMPERATURE
            println("Target temperature changed to $newTarget")
            hvacControl.desiredTemperature = newTarget
            false
        }
    )

    return {
        val input = it.split(' ').toTypedArray()
        val cmd = mappings.getOrDefault(
            Pair(input[0],
            input.getOrNull(1))) {
                println("Please enter a valid command"); false
            };

        {
            cmd(input.getOrNull(2))
        }

    }
}

fun main() {
    val hvacControl = wireItUp()
    val route = initRouting(hvacControl)

    var terminate = false
    while (!terminate) {
        val input = readLine() ?: ""
        terminate = route(input).invoke()
    }
}


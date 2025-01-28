package HiddenAbilitySpawns.events

import HiddenAbilitySpawns.HiddenAbilitySpawns
import HiddenAbilitySpawns.config.Configuration
import HiddenAbilitySpawns.util.Utils
import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import kotlin.random.Random

class SpawnEvent(private val config: Configuration) {

    private val utils = Utils()

    init {
        // Subscribe to Cobblemon's Pokémon spawn event
        CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe(priority = Priority.HIGH) { event ->
            val pokemonEntity = event.entity as? PokemonEntity ?: return@subscribe
            val pokemon = pokemonEntity.pokemon

            val config = HiddenAbilitySpawns.getMainConfig()
                ?: return@subscribe // exit if null

            val hiddenAbilityChance = config.getDouble("HiddenAbilityChance", 0.005)

            // Roll to see if we should apply a Hidden Ability
            if (Random.nextDouble() < hiddenAbilityChance) {
                // Set the hidden ability (if the species/form supports it)
                utils.setHiddenAbility(pokemon)

                // Check if the Pokémon actually ended up with a Hidden Ability
                if (utils.pokemonHasHiddenAbility(pokemon)) {
                    HiddenAbilitySpawns.LOGGER.info(
                        "${pokemon.species.name} spawned with its hidden ability: ${pokemon.ability.name}"
                    )
                }
            }
        }
    }
}

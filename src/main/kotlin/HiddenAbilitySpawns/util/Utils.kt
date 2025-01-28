package HiddenAbilitySpawns.util

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.abilities.AbilityTemplate
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.events.pokemon.ShinyChanceCalculationEvent
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.FormData
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.pokemon.properties.HiddenAbilityProperty
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import java.util.*

// Thanks Guitar for letting me use this
class Utils {


    fun formHasHiddenAbility(pokemon: Pokemon): AbilityTemplate? {
        pokemon.form.abilities.forEach { ability ->
            if (ability.type.toString().contains("Hidden"))
                return ability.template
        }
        return null
    }

    private fun getSpeciesHiddenAbilities(species: Species): MutableList<String> {
        val list = mutableListOf<String>()
        species.abilities.forEach {
            if (it.type.toString().contains("Hidden")) {
                list.add(it.template.name)
            }
        }

        species.forms.forEach { form ->
            form.abilities.forEach {
                if (it.type.toString().contains("Hidden")) {
                    list.add(it.template.name)
                }
            }
        }

        return list
    }

    fun hasFormHiddenAbility(pokemon: Pokemon, form: FormData?): Boolean {
        if (form == null)
            return false

        form.abilities.forEach {
            if (it.type.toString().contains("Hidden"))
                return true
        }
        return false
    }

    fun hasSpeciesHiddenAbility(pokemon: Pokemon, species: Species?): Boolean {
        if (species == null)
            return false

        val abilities = getSpeciesHiddenAbilities(species)
        return abilities.contains(pokemon.ability.name)
    }

    fun pokemonHasHiddenAbility(pokemon: Pokemon): Boolean {
        val currentAbility = pokemon.ability.template.name

        if (pokemon.form != pokemon.species.standardForm) {
            var form: FormData? = null
            pokemon.species.forms.forEach {
                if (it.name == pokemon.form.name)
                    form = it
            }
            form?.abilities?.forEach {
                if (it.type.toString().contains("Hidden"))
                    return true
            }
        }

        pokemon.species.abilities.forEach {
            if (it.type.toString().contains("Hidden")) {
                if (currentAbility == it.template.name)
                    return true
            }
        }
        return false
    }

    fun setHiddenAbility(pokemon: Pokemon) {
        HiddenAbilityProperty(true).apply(pokemon)
    }

}
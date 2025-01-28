package HiddenAbilitySpawns.util

import com.cobblemon.mod.common.pokemon.FormData
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.properties.HiddenAbilityProperty

// Thanks Guitar for letting me use this
class Utils {

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
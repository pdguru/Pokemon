package com.pdg.pokemon.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.pdg.pokemon.models.Evolution
import com.pdg.pokemon.models.EvolutionChain
import com.pdg.pokemon.models.Pokemon
import com.pdg.pokemon.models.PokemonSpecies
import com.pdg.pokemon.utils.NetworkHelper
import java.util.ArrayList

class SecondVM(val name: String) : ViewModel() {

    class CustomViewModelFactory(private val url: String) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SecondVM(url) as T
        }

    }

    var evolutionUrl = "https://pokeapi.co/api/v2/evolution-chain/"
    var pokemonIdurl = "https://pokeapi.co/api/v2/pokemon-species/${name}"

    lateinit var pokemons: MutableLiveData<List<PokemonSpecies>>

    init {
        var evoUrl = getPokemonChain().evolution_chain?.url!!
        evolutionUrl = evoUrl
        getEvolutionList()
    }

    private fun getPokemonChain(): Pokemon {
        Log.i("POKEMON", "ℹ️ pokemonURL: $pokemonIdurl")
        var resp = NetworkHelper().execute(pokemonIdurl).get()
        Log.i("POKEMON", "Pokemon info as Response: $resp")

        val json = Gson().fromJson(resp, Pokemon::class.java)
        Log.i("POKEMON", "Pokemon id as jsonResult: ${json.id}")
        Log.i("POKEMON", "Pokemon chain as jsonResult: ${json.evolution_chain?.url}")

        return json
    }

    private fun getEvolutionList(): MutableLiveData<List<PokemonSpecies>> {
        Log.i("POKEMON", "ℹ️ EvolutionURL: $evolutionUrl")
        pokemons = MutableLiveData()

        var resp = NetworkHelper().execute(evolutionUrl).get()
        Log.i("POKEMON", "EvolutionChain Response: $resp")

        val json = Gson().fromJson(resp, Evolution::class.java)
        Log.i("POKEMON", "EvolutionChain as jsonResult: ${json.chain?.species?.name}")

        val i = ArrayList<PokemonSpecies>()

        if (name == json.chain?.species?.name!!) {
            if (json.chain?.evolves_to?.get(0) != null) {
                i.add(json.chain?.evolves_to?.get(0)?.species!!)
                pokemons.value = i
            }
        } else {
            pokemons.value = chainIterator(json.chain?.evolves_to)
        }
        return pokemons
    }

    fun chainIterator(p: List<EvolutionChain>?): ArrayList<PokemonSpecies> {
        val ia = ArrayList<PokemonSpecies>()
        var node: EvolutionChain = p?.get(0)!!
        Log.i("POKEMON", "ℹ️Evolution Species: ${node.species?.name!!}")
        if (name == node.species?.name) {
            if (node.evolves_to?.isNullOrEmpty() == false) {
                ia.add(node.evolves_to?.get(0)?.species!!)

            } else {
                node = node.evolves_to?.get(0)!!
                val i = ArrayList<EvolutionChain>()
                Log.i("POKEMON", "ℹ️Evolution Species: ${node.species?.name!!}")
                i.add(node)
                chainIterator(i)
            }
        }
        return ia
    }
}
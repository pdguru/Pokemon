package com.pdg.pokemon.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import com.pdg.pokemon.models.Evolution
import com.pdg.pokemon.models.PokemonSpecies
import com.pdg.pokemon.utils.NetworkHelper
import java.util.ArrayList

class SecondVM: ViewModel() {

    lateinit var pokemons: MutableLiveData<List<PokemonSpecies>>

    init {
        getEvolutionList()
    }

    private fun getEvolutionList(): MutableLiveData<List<PokemonSpecies>> {
        pokemons = MutableLiveData()

        var resp = NetworkHelper().execute("https://pokeapi.co/api/v2/evolution-chain/1").get()
        Log.i("POKEMON", "🦄 EvolutionChain Response: $resp")

        val json = Gson().fromJson(resp, Evolution::class.java)
        Log.i("POKEMON", "🦄 EvolutionChain as jsonResult: ${json.chain?.species?.name}")

        val i = ArrayList<PokemonSpecies>()
        for (p in json.chain?.evolves_to!!) {
            i.add(p.species)
        }
        pokemons.value = i


        return pokemons
    }
}
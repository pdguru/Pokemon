package com.pdg.pokemon.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import com.pdg.pokemon.models.PokemonSpecies
import com.pdg.pokemon.models.Result
import com.pdg.pokemon.utils.NetworkHelper
import java.util.*

class MainActivityVM : ViewModel() {

    lateinit var pokemons: MutableLiveData<List<PokemonSpecies>>

    init {
        getInitialList()
    }

    private fun getInitialList(): MutableLiveData<List<PokemonSpecies>> {
        pokemons = MutableLiveData()

        var resp = NetworkHelper().execute("https://pokeapi.co/api/v2/pokemon?limit=26").get()
        Log.i("POKEMON", "🦄 Response: $resp")

        val json = Gson().fromJson(resp, Result::class.java)
        Log.i("POKEMON", "🦄 as jsonResult: ${json}")

        val i = ArrayList<PokemonSpecies>()
        for (p in json.results) {
            i.add(p)
        }
        pokemons.value = i


        return pokemons
    }
}
package com.pdg.pokemon.utils

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyHttpService: IntentService("MyService"){

    override fun onHandleIntent(intent: Intent?) {
        var pokemons = NetworkHelper.downloadUrl("https://pokeapi.co/api/v2/pokemon?limit=26")
        Log.i("🦄 POKEMON","Response: $pokemons")
    }


}
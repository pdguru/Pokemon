package com.pdg.pokemon.utils

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.google.gson.Gson
import com.pdg.pokemon.models.Pokemon
import java.io.IOException

class MyHttpService: IntentService("MyService"){

    override fun onHandleIntent(intent: Intent?) {
        var pokemons: String?
        try{
            pokemons = NetworkHelper.downloadUrl("https://pokeapi.co/api/v2/pokemon?limit=26")
            Log.i("🦄 POKEMON","Response: $pokemons")

            val respJson = Gson().toJson(pokemons)
            Log.i("🦄 POKEMON","Response JSON: $respJson")

        }catch (e: IOException){
            e.printStackTrace()
            return
        }

        var responseIntent = Intent("MyService")
        responseIntent.putExtra("RESPONSE_PAYLOAD",pokemons)

        var localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext)
        localBroadcastManager.sendBroadcast(responseIntent)

    }
}
package com.pdg.pokemon.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log


class NetworkManager {
    fun hasNetworkAccess(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        } catch (e: Exception) {
            Log.e("POKEMON","Error: ${e.message}")
            e.printStackTrace()
            return false
        }

    }
}
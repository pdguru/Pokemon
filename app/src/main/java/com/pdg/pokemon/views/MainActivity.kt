package com.pdg.pokemon.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pdg.pokemon.R
import com.pdg.pokemon.viewmodels.MainActivityVM
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.pdg.pokemon.utils.MyHttpService


class MainActivity : AppCompatActivity() {

//    lateinit var mainViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this).get(MainActivityVM::class.java)

        mainViewModel.pokemons.observe(this, Observer {
            Log.i("POKEMON", "Received: ${it?.size}")
        })


//        var intent = Intent(this, MyHttpService::class.java)
//        startService(intent)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val message = intent.getStringExtra("RESPONSE_PAYLOAD")
            Log.d("receiver", "Got message: $message")
        }
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
            broadcastReceiver
        )
        super.onPause()
    }

    override fun onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
            broadcastReceiver, IntentFilter("MyService")
        )
        super.onResume()
    }
}
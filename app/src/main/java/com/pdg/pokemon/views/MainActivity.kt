package com.pdg.pokemon.views

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pdg.pokemon.R
import com.pdg.pokemon.utils.MyHttpService
import com.pdg.pokemon.utils.NetworkHelper
import com.pdg.pokemon.viewmodels.MainActivityVM

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityVM::class.java)

        var intent = Intent(this,MyHttpService::class.java)
        startService(intent)
    }
}
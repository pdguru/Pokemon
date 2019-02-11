package com.pdg.pokemon.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pdg.pokemon.R
import com.pdg.pokemon.models.PokemonSpecies
import com.pdg.pokemon.utils.PokemonAdapter
import com.pdg.pokemon.viewmodels.MainActivityVM
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this).get(MainActivityVM::class.java)

        mainViewModel.pokemons.observe(this, Observer { basicPokemons ->
            Log.i("POKEMON", " Received: ${basicPokemons?.size}")
            mainListView.adapter = PokemonAdapter(this@MainActivity, basicPokemons!!)

        })

        mainListView.setOnItemClickListener { parent, view, position, id ->
            Log.i("POKEMON", " clicked: $position")
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("SELECTED", mainListView.adapter.getItem(position) as PokemonSpecies)
            startActivity(intent)
        }
    }
}
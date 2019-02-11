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
import com.pdg.pokemon.viewmodels.SecondVM
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secondViewModel = ViewModelProviders.of(this).get(SecondVM::class.java)

        val intent = intent.extras.get("SELECTED") as PokemonSpecies
        Log.i("POKEMON","🦄😸 Selected pokemon: ${intent.name}, ${intent.url}")

        secondViewModel.pokemons.observe(this, Observer { basicPokemons ->
            Log.i("POKEMON", "😸 Received: ${basicPokemons?.size}")
            mainListView.adapter = PokemonAdapter(this@SecondActivity, basicPokemons!!)

        })

        mainListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@SecondActivity,SecondActivity::class.java)
            intent.putExtra("SELECTED", mainListView.adapter.getItem(position) as PokemonSpecies)
            startActivity(intent)
        }
    }
}
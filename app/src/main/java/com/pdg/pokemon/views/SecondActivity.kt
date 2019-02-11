package com.pdg.pokemon.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.pdg.pokemon.R
import com.pdg.pokemon.models.PokemonSpecies
import com.pdg.pokemon.utils.PokemonAdapter
import com.pdg.pokemon.viewmodels.SecondVM
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentPokemon = intent.extras.get("SELECTED") as PokemonSpecies
        Log.i("POKEMON", "Selected pokemon: ${intentPokemon.name}, ${intentPokemon.url}")

        //make an initial call to get the pokemon id using the url

        val secondViewModel =
            ViewModelProviders.of(this, SecondVM.CustomViewModelFactory(intentPokemon.name)).get(SecondVM::class.java)

        secondViewModel.pokemons.observe(this, Observer { basicPokemons ->
            Log.i("POKEMON", "Received: ${basicPokemons?.size}")
            if(basicPokemons.isNullOrEmpty()){
                Toast.makeText(this,"Pokemon cannot evolve further!", Toast.LENGTH_LONG).show()
                finish()
            }else {
                mainListView.adapter = PokemonAdapter(this@SecondActivity, basicPokemons!!)
            }
        })
    }
}
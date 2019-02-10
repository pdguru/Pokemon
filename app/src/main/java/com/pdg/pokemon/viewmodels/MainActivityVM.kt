package com.pdg.pokemon.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.pdg.pokemon.models.Pokemon
import com.pdg.pokemon.utils.NetworkHelper

class MainActivityVM : ViewModel(){

    lateinit var pokemons : LiveData<Pokemon>




}

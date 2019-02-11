package com.pdg.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    var count: Int,
    var next: String,
    var previous: String,
    var results: Array<BasicPokemon>
):Parcelable
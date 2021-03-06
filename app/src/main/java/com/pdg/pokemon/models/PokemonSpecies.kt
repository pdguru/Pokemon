package com.pdg.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSpecies (
    var name: String,
    var url: String
): Parcelable

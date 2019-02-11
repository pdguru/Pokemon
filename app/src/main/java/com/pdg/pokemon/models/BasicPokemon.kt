package com.pdg.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BasicPokemon (
    var name: String,
    var url: String
): Parcelable

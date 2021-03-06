package com.pdg.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    var id: Int,
    var evolution_chain: ChainUrl?

) : Parcelable
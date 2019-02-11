package com.pdg.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Evolution (
    var id: Int,
    var baby_trigger_item: String?,
    var chain: EvolutionChain?
): Parcelable
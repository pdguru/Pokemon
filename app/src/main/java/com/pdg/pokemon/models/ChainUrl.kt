package com.pdg.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChainUrl (
    var url: String?
): Parcelable

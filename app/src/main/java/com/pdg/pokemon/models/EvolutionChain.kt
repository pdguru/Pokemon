package com.pdg.pokemon.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EvolutionChain (
    var evolves_to: List<EvolutionChain>?,
    var is_baby: Boolean,
    var species: PokemonSpecies
): Parcelable

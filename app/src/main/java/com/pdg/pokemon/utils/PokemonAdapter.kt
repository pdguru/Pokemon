package com.pdg.pokemon.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.pdg.pokemon.R
import com.pdg.pokemon.models.PokemonSpecies

class PokemonAdapter(val context: Context, val pokemonSpecies: List<PokemonSpecies>) :
    BaseAdapter() {

    override fun getItem(position: Int): Any {
        return pokemonSpecies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return pokemonSpecies.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View

        val inflater = LayoutInflater.from(context)
        rowView = inflater.inflate(R.layout.list_item, parent, false)

        val text = rowView.findViewById<TextView>(R.id.itemTextView)
        text.text = pokemonSpecies[position].name

        return rowView

    }
}

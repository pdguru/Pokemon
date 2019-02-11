package com.pdg.pokemon.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.pdg.pokemon.R
import com.pdg.pokemon.models.BasicPokemon

class PokemonAdapter(val context: Context, val basicPokemons: List<BasicPokemon>) :
    BaseAdapter() {

    override fun getItem(position: Int): Any {
        return basicPokemons[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return basicPokemons.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View

        val inflater = LayoutInflater.from(context)
        rowView = inflater.inflate(R.layout.list_item, parent, false)

        val text = rowView.findViewById<TextView>(R.id.itemTextView)
        text.text = basicPokemons[position].name

        return rowView

    }
}

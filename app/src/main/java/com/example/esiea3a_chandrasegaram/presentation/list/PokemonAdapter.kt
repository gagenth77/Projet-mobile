package com.example.esiea3a_chandrasegaram.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea3a_chandrasegaram.pokemon.Pokemon
import com.example.esiea3a_chandrasegaram.R

class PokemonAdapter(private val dataSet: Array<Pokemon>?) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class PokemonViewHolder(pokemon: View) : RecyclerView.ViewHolder(pokemon) {
        var idPokemon: TextView
        var nomPokemon: TextView
        var type1: TextView
        var type2: TextView

        init {
            idPokemon = pokemon.findViewById(R.id.id)
            nomPokemon = pokemon.findViewById(R.id.name)
            type1 = pokemon.findViewById(R.id.type1)
            type2 = pokemon.findViewById(R.id.type2)
        }
    }

    fun updateList(list: Array<Pokemon>?){
        var dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.PokemonViewHolder {
        val poke = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_issue, parent, false)
        return PokemonViewHolder(poke)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val poke = this.dataSet!![position]
        val type1 = poke.types?.get(1)?.type!!.name
        val type2 = poke.types?.get(2)?.type!!.name
        holder.idPokemon.append(poke.id.toString())
        holder.idPokemon.append(poke.name.toString())
        holder.idPokemon.append(type1)
        holder.idPokemon.append(type2)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size


}
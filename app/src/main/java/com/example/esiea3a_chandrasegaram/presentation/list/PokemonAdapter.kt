package com.example.esiea3a_chandrasegaram.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea3a_chandrasegaram.pokemon.Pokemon
import com.example.esiea3a_chandrasegaram.R

class PokemonAdapter(private val dataSet: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(pokemon: View) : RecyclerView.ViewHolder(pokemon) {
        //val textView: TextView
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

    fun updateList(list: List<String>){
        var dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val poke = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_issue, parent, false)
        return ViewHolder(poke)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val poke = dataSet[position]
        val type1 = poke.types?.get(1)?.type!!.name
        val type2 = poke.types?.get(2)?.type!!.name
        viewHolder.idPokemon.append(poke.id.toString())
        viewHolder.idPokemon.append(poke.name.toString())
        viewHolder.idPokemon.append(type1)
        viewHolder.idPokemon.append(type2)
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.textView.text = dataSet[position]

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
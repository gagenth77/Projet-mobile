package com.example.esiea3a_chandrasegaram.API

import com.example.esiea3a_chandrasegaram.pokemon.Pokemon
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIRepository private constructor(){
    private var callPokemon: CallPokemon? = null
    private fun creatCallPokemon() {
        callPokemon = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CallPokemon::class.java)
    }

    fun getPokemonList(limit: Int): Call<ListResponse> {
        println("on rentre ici")
        return callPokemon!!.getPokemonList(limit)
    }

    fun getPokemon(name: String): Call<Pokemon> {
        return callPokemon!!.getPokemon(name)
    }

    companion object {
        @Volatile
        var instance: APIRepository? = null
            get() {
                if (field == null) {
                    field = APIRepository()
                }
                return field
            }
            private set
    }

    init {
        creatCallPokemon()
    }
}
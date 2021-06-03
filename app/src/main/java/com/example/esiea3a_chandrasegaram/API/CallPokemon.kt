package com.example.esiea3a_chandrasegaram.API

import com.example.esiea3a_chandrasegaram.pokemon.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CallPokemon {
    @GET("pokemon/")
    fun getPokemonList(@Query("limit") limit: Int): retrofit2.Call<ListResponse>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): retrofit2.Call<Pokemon>
}
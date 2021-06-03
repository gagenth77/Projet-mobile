package com.example.esiea3a_chandrasegaram

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea3a_chandrasegaram.API.APIRepository
import com.example.esiea3a_chandrasegaram.API.ListResponse
import com.example.esiea3a_chandrasegaram.pokemon.Pokemon
import com.example.esiea3a_chandrasegaram.presentation.list.PokemonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var pokemonRecyclerView: RecyclerView? = null
    private lateinit var pokemonAdapter: PokemonAdapter
    private var apiRepository: APIRepository? = APIRepository.instance
    var pokemonsToShow: Array<Pokemon>? =null
    var listPokemon: Array<PokePath>? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView = findViewById(R.id.pokemon_recyclerview)
        pokemonAdapter = PokemonAdapter(pokemonsToShow)
        //val linearLayoutManager = LinearLayoutManager(this)
        //pokemonRecyclerView.setLayoutManager(linearLayoutManager)
        //pokemonRecyclerView.setAdapter(pokemonAdapter)
        findViewById<Button>(R.id.fab).setOnClickListener {
            getlistPoke()
            pokemonAdapter.updateList(pokemonsToShow)
            pokemonAdapter.notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getlistPoke():Unit {
        val limit = 20
        apiRepository!!.getPokemonList(limit)
                .enqueue(object : Callback<ListResponse?> {

                    override fun onResponse(call: Call<ListResponse?>,response: Response<ListResponse?>) {
                        val listResponse = response.body()
                        Log.d("BODY",listResponse.toString())
                        listPokemon = listResponse?.results
                        getPokemons(listPokemon!!)
                    }
                    override fun onFailure(call: Call<ListResponse?>, t: Throwable) {
                        println(t.message)
                    }
                })
    }

    fun getPokemons(list: Array<PokePath>): Unit {
        var name : String
        for(i in 0..list.size - 1){
            name = list[i].name
            println(name)
            apiRepository!!.getPokemon(name).enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    val pokemon= response.body()
                    pokemonsToShow?.plus(pokemon!!)
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    println(t.message)
                }
            })
        }
    }
}
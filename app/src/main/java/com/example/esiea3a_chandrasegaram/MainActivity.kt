package com.example.esiea3a_chandrasegaram

import android.icu.text.CaseMap
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.esiea3a_chandrasegaram.API.APIRepository
import com.example.esiea3a_chandrasegaram.API.ListResponse
import com.example.esiea3a_chandrasegaram.pokemon.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var apiRepository: APIRepository? = APIRepository.instance
    var url: String? = ""
    var pokemonsToShow: MutableList<Pokemon>? =null
    var listPokemon: MutableList<Pokemon>? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        println("NEW APP CREATED")
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            getlistPoke()
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
        var id: Int
        var list: Array<PokePath>? = null
        apiRepository!!.getPokemonList(limit)
                .enqueue(object : Callback<ListResponse?> {
                    override fun onResponse(call: Call<ListResponse?>,response: Response<ListResponse?>) {
                        val listResponse = response.body()
                        list = listResponse?.results
                    }
                    override fun onFailure(call: Call<ListResponse?>, t: Throwable) {
                        println(t.message)
                    }
                })
        for(i in 0..limit-1){
            url = list?.get(i)?.url
            println("L'URL EST :"+url)
            id = i+1
            println(id)
            apiRepository!!.getPokemon(id)
                    .enqueue(object : Callback<Pokemon> {
                        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                            print("On entre ici au bon moment ?")
                            var pokemon: Pokemon = response.body()!!
                            listPokemon!!.add(pokemon)
                        }

                        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                            println(t.message)
                        }

                    })
        }
    }
}
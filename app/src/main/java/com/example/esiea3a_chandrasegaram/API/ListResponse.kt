package com.example.esiea3a_chandrasegaram.API

import com.example.esiea3a_chandrasegaram.PokePath

class ListResponse {
    var count: Int? = null
    var next: String? = null
    var previous: String? = null
    var results: Array<PokePath>? = null
}
package com.magine.model

import java.io.Serializable

class External : Serializable {
    lateinit var imdb: String
    var tvrage: Long = 0
    var thetvdb: Long = 0
}
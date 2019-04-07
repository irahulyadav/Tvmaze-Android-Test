package com.magine.model

import java.io.Serializable

class ShowLink : Serializable {
    lateinit var self: Href
    lateinit var previousepisode: Href

    class Href : Serializable {
        lateinit var href: String
    }
}
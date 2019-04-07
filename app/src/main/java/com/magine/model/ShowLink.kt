package com.magine.model

class ShowLink{
    lateinit var self: Href
    lateinit var previousepisode: Href

    class Href{
        lateinit var href: String
    }
}
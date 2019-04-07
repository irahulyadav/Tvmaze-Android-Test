package com.magine.model

import java.io.Serializable

class Network : Serializable {
    var id: Int = 0
    lateinit var name: String
    lateinit var country: Country
}
package com.magine.model

import java.io.Serializable

class Schedule: Serializable{
    lateinit var time: String
    lateinit var days: Array<String>
}
package com.magine.model

import java.io.Serializable

class Schedule: Serializable{
    lateinit var time: String
    lateinit var days: Array<String>

    val info: String
        get() {
            val buffer = StringBuffer()
            days.forEach { s ->
                if (!buffer.isEmpty()) {
                    buffer.append(", ")
                }
                buffer.append(s)
            }
            return buffer.append(" at ").append(time).toString()
        }
}
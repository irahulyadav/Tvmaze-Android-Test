package com.magine.model

import android.content.Intent
import android.os.Bundle
import java.io.Serializable

class Show : Serializable {
    var id: Int = 0
    lateinit var url: String
    lateinit var name: String
    lateinit var type: String
    lateinit var language: String
    lateinit var status: String
    var runtime: Int = 0
    lateinit var premiered: String
    lateinit var officialSite: String
    var weight: Int = 0
    lateinit var summary: String
    var webChannel: WebChannel? = null
    lateinit var genres: Array<String>
    lateinit var schedule: Schedule
    lateinit var rating: Rating
    lateinit var network: Network

    lateinit var externals: External
    var image: ShowImage? = null
    lateinit var _links: ShowLink

    val genresInfo: String
        get() {
            val buffer = StringBuffer()
            genres.forEach { s ->
                if (!buffer.isEmpty()) {
                    buffer.append(" | ")
                }
                buffer.append(s)
            }
            return buffer.toString()
        }

    val bundle: Bundle
        get() {
            return Bundle().apply {
                putSerializable("Show", this@Show)
            }
        }

    companion object {
        fun get(intent: Intent): Show? {
            return if (intent.hasExtra("Show")) intent.getSerializableExtra("Show") as Show else null
        }

        fun get(bundle: Bundle): Show? {
            return if (bundle.containsKey("Show")) bundle.getSerializable("Show") as Show else null
        }
    }
}
package com.magine.model

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
    lateinit var image: ShowImage
    lateinit var _links: ShowLink
}
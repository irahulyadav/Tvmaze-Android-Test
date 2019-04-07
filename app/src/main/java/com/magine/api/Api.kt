package com.magine.api

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.magine.model.Show
import org.json.JSONArray
import org.json.JSONObject

class Api {
    interface VolleyListener<T> : Response.ErrorListener, Response.Listener<T> {
        override fun onResponse(response: T)

        override fun onErrorResponse(error: VolleyError)
    }

    class ShowList(search: String?, listener: VolleyListener<JSONArray>) :
        AuthJsonObjectRequest<JSONArray>(
            Request.Method.GET,
            if (search == null || search.isEmpty())
                "http://api.tvmaze.com/shows"
            else "http://api.tvmaze.com/search/shows?q=$search",
            listener = listener
        ) {
    }

    class ShowInfo(show: Show, listener: VolleyListener<JSONObject>):
        AuthJsonObjectRequest<JSONObject>(
            Request.Method.GET,
            "http://api.tvmaze.com/shows/${show.id}",
            listener = listener
        ) {
    }

    //http://api.tvmaze.com/shows/1/episodes
    class EpisodesList(show: Show, listener: VolleyListener<JSONObject>) :
        AuthJsonObjectRequest<JSONObject>(
            Request.Method.GET,
            "http://api.tvmaze.com/shows/${show.id}/episodes",
            listener = listener
        ) {
    }
}

package com.magine

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.magine.api.Api
import com.magine.model.Show
import kotlinx.android.synthetic.main.activity_show_info.*
import kotlinx.android.synthetic.main.content_show_info.*
import org.json.JSONObject

class ShowInfoActivity : AppCompatActivity(), Api.VolleyListener<JSONObject> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_info)
        setSupportActionBar(toolbar)
        val show = Show.get(intent)
        if (show == null) {
            finish()
            return
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        Api.ShowInfo(show, this).call(this)
    }

    fun displayInfo(show: Show) {
        tvInfo.text = show.summary
    }

    override fun onResponse(response: JSONObject) {
        val show: Show = Gson().fromJson(response.toString(), Show::class.java)
        displayInfo(show)
    }

    override fun onErrorResponse(error: VolleyError) {

    }
}

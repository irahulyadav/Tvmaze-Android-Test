package com.magine

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.magine.api.Api
import com.magine.model.Show

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity: AppCompatActivity(), Api.VolleyListener<JSONArray> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Api.ShowList("girls", listener = this).call(this)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResponse(response: JSONArray) {
        print(response)
        val list: Array<Info> = Gson().fromJson(response.toString(), Array<Info>::class.java)
        print(list)

        Api.ShowInfo(list.first().show, object : Api.VolleyListener<JSONObject>{
            override fun onResponse(response: JSONObject) {
                val show: Show = Gson().fromJson(response.toString(), Show::class.java)
                print(show)
            }

            override fun onErrorResponse(error: VolleyError) {

            }
        }).call(this)
    }

    override fun onErrorResponse(error: VolleyError) {

    }
}

class Info{
    var score: Double = 0.0
    lateinit var show: Show
}

package com.magine

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.magine.adapter.ShowListAdapter
import com.magine.api.Api
import com.magine.listener.RecyclerListener
import com.magine.model.Show
import com.magine.view.RecycleViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray

class MainActivity: AppCompatActivity(), Api.VolleyListener<JSONArray> {

    lateinit var adapter: ShowListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = ShowListAdapter(this, listener = object : RecyclerListener<ShowInfo>() {
            override fun onItemClick(holder: RecycleViewHolder<ShowInfo>, value: ShowInfo) {
                val intent = Intent(this@MainActivity, ShowInfoActivity::class.java)
                intent.putExtras(value.show.bundle)
                startActivity(intent)
            }
        })
        setSupportActionBar(toolbar)
        Api.ShowList("girls", listener = this).call(this)

        with(list) {
            layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
            adapter = this@MainActivity.adapter
        }

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
        val list = ArrayList<ShowInfo>()
        list.addAll(Gson().fromJson(response.toString(), Array<ShowInfo>::class.java))
        adapter.notifyDataChanged(list)
    }

    override fun onErrorResponse(error: VolleyError) {

    }
}

class ShowInfo {
    var score: Double = 0.0
    lateinit var show: Show
}

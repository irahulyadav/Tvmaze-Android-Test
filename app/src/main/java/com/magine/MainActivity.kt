package com.magine

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.magine.adapter.ShowListAdapter
import com.magine.api.Api
import com.magine.listener.RecyclerListener
import com.magine.model.Show
import com.magine.view.AlertView
import com.magine.view.RecycleViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray

class MainActivity: AppCompatActivity(), Api.VolleyListener<JSONArray> {

    lateinit var adapter: ShowListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = ShowListAdapter(this, listener = object : RecyclerListener<Show>() {
            override fun onItemClick(holder: RecycleViewHolder<Show>, value: Show) {
                val intent = Intent(this@MainActivity, ShowInfoActivity::class.java)
                intent.putExtras(value.bundle)
                startActivity(intent)
            }
        })
        setSupportActionBar(toolbar)


        with(list) {
            layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
            adapter = this@MainActivity.adapter
        }

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        search()
    }

    fun search(text: String? = null) {
        Api.ShowList(text, listener = this).call(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)


        val searchView = menu.findItem(R.id.search).actionView as SearchView
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.queryHint = getString(R.string.search)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 == null || p0.isEmpty()) {
                    search(p0)
                }
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                search(p0)
                return true
            }
        })
        return true
    }


    override fun onResponse(response: JSONArray) {
        val list = ArrayList<Show>()
        try {

            Gson().fromJson(response.toString(), Array<ShowInfo>::class.java).forEach {
                list.add(it.show)
            }

        } catch (e: Exception) {
            list.addAll(Gson().fromJson(response.toString(), Array<Show>::class.java))
        }
        adapter.notifyDataChanged(list)
    }

    override fun onErrorResponse(error: VolleyError) {
        AlertView.showConfirmDialog(
            this,
            message = "There is some problem with request, Would you like to retry",
            button = "retry",
            action = {
                search()
            });
    }
}

class ShowInfo {
    var score: Double = 0.0
    lateinit var show: Show
}

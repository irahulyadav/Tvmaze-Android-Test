package com.magine.adapter


import com.magine.listener.RecyclerViewListener
import com.magine.view.RecycleViewHolder
import java.util.*

/**
 * Created by iprahul on 9/27/16.
 */

abstract class RecyclerViewSearchAdapter<V : RecycleViewHolder<T>, T>(listener: RecyclerViewListener<T>?) :
        RecyclerViewAdapter<V, T>(listener) {

    protected var originalList: MutableList<T> = ArrayList()

    var filter: String? = null
        set(filter) {
            field = filter
            notifyDataChanged()
        }


    val total: Int
        get() = originalList.size

    var offlineSearch = true


    override fun notifyDataChanged() {
        list.clear()
        val filter = this.filter
        if (filter == null || filter.isEmpty()) {
            list.addAll(originalList)
            super.notifyDataChanged()
            return
        }

        for (s in originalList) {
            if (isValid(s, filter.toLowerCase())) {
                list.add(s)
            }
        }
        super.notifyDataChanged()
    }

    override fun notifyAddMore(list: List<T>) {
        originalList.addAll(list)
        notifyDataChanged()
    }

    override fun notifyDataChanged(list: List<T>) {
        originalList.clear()
        originalList.addAll(list)
        notifyDataChanged()
    }


    override fun notifyRemoveItem(item: T?): Boolean {
        if (originalList.contains(item)) {
            originalList.remove(item)
            notifyDataChanged()
            return true
        }
        return false
    }


    /*
    Ex
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_search, menu);
            adapter?.setSearchView(menu, R.id.search, this);
            return super.onCreateOptionsMenu(menu);
        }*/


    override fun clearList() {
        originalList.clear()
        super.clearList()
    }


    abstract fun isValid(item: T, filter: String): Boolean

}

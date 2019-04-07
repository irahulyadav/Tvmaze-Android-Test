package com.magine.listener

import android.support.v7.widget.RecyclerView
import android.view.View
import com.magine.view.RecycleViewHolder


/**
 * Created by iprahul on 9/14/16.
 */

interface RecyclerViewListener<T> : RecyclerView.RecyclerListener {
    fun onItemClick(holder: RecycleViewHolder<T>, value: T)

    fun onItemLongClick(holder: RecycleViewHolder<T>, value: T)

    fun onItemSelect(holder: RecycleViewHolder<T>, value: T)

    fun onItemViewClick(holder: RecycleViewHolder<T>, view: View, value: T)

    fun onItemViewLongClick(holder: RecycleViewHolder<T>, view: View, value: T)

    fun loadMore()

    fun afterNotifyData()

    override fun onViewRecycled(holder: RecyclerView.ViewHolder)
}

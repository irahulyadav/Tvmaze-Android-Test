package com.magine.listener

import android.support.v7.widget.RecyclerView
import android.view.View
import com.magine.view.RecycleViewHolder


/**
 * Created by iprahul on 9/28/16.
 */

open class RecyclerListener<T> : RecyclerViewListener<T> {
    override fun onItemClick(holder: RecycleViewHolder<T>, value: T) {

    }


    override fun onItemLongClick(holder: RecycleViewHolder<T>, value: T) {

    }

    override fun onItemSelect(holder: RecycleViewHolder<T>, value: T) {

    }

    override fun onItemViewClick(holder: RecycleViewHolder<T>, view: View, value: T) {

    }

    override fun onItemViewLongClick(holder: RecycleViewHolder<T>, view: View, value: T) {

    }

    override fun loadMore() {

    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {

    }

    override fun afterNotifyData() {

    }
}

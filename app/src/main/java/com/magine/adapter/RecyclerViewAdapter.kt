package com.magine.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.magine.listener.RecyclerViewListener
import com.magine.view.RecycleViewHolder

import java.util.*

/**
 * Created by iprahul on 9/20/16.
 */

abstract class RecyclerViewAdapter<V : RecycleViewHolder<T>, T>(var listener: RecyclerViewListener<T>? = null) :
        RecyclerView.Adapter<V>() {
    protected var list: MutableList<T> = ArrayList()

    val isEmpty: Boolean
        get() = list.isEmpty()



    open fun sort() {

    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.setItem(getItem(position))
        if (itemCount == position + 1) {
            listener?.loadMore()
        }
    }


    open fun notifyAddMore(list: List<T>) {
        this.list.addAll(list)
        notifyDataChanged()
    }

    open fun notifyDataChanged(list: List<T>) {
        clearList()
        this.list.addAll(list)
        notifyDataChanged()
    }

    open fun notifyDataChanged() {
        sort()
        notifyDataSetChanged()
        listener?.afterNotifyData()
    }

    open fun notifyClearData() {
        clearList()
        notifyDataSetChanged()
        listener?.afterNotifyData()
    }

    open fun notifyRemoveAt(position: Int) {
        notifyRemoveItem(getItem(position))
    }

    open fun notifyRemoveItem(item: T?): Boolean {
        if (item != null && list.contains(item)) {
            list.remove(item)
            notifyDataChanged()
            return true
        }
        return false
    }

    fun notifyRemoveItem(position: Int): Boolean {
        return itemCount > position && notifyRemoveItem(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return getItemView(parent)
    }

    abstract fun getItemView(parent: ViewGroup): V


    override fun getItemCount(): Int {
        return list.size
    }

    open fun getItem(position: Int): T? {
        return if (list.count() <= position) null else list[position]
    }

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(list, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }


    open fun onLeftSwipe(viewHolder: RecyclerView.ViewHolder) {

    }

    open fun onRightSwipe(viewHolder: RecyclerView.ViewHolder) {

    }

    open fun clearList() {
        list.clear()
    }
}

package com.magine.view

import android.content.Context
import android.support.annotation.RawRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.magine.listener.RecyclerViewListener

/**
 * Created by iprahul on 9/14/16.
 */

open class RecycleViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnLongClickListener,
        View.OnClickListener {
    var recyclerListener: RecyclerViewListener<T>? = null

    var value: T? = null
        private set(value) {
            field = value
        }

    val context: Context
        get() = itemView.getContext()

    //    var visibility: Int
//        get() = itemView.visibility
//        set(visibility) {
//            itemView.visibility = visibility
//        }
//set background view here like swipe to left or right()

    @RawRes
    var backgroundResource: Int = 0
        set(value) {
            field = value
            foreground?.setBackgroundResource(value)
            itemView.setBackgroundResource(value)
        }
    var background: View? = null

    //set main item view here
    var foreground: View? = null

    init {
        itemView.tag = this
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    constructor(itemView: View, recyclerListener: RecyclerViewListener<T>?) : this(itemView) {
        this.recyclerListener = recyclerListener
    }

    fun close() {
        foreground?.translationX = 0F
        foreground?.x = 0F
        foreground?.invalidate()
    }

    fun <V : View> findViewById(id: Int): V {
        return itemView.findViewById(id)
    }

    override fun onClick(view: View) {
        if (value == null) {
            return
        }
        recyclerListener?.onItemClick(this, value!!)
    }

    open fun setItem(value: T?) {
        this.value = value
        itemView.tag = value
    }


    override fun onLongClick(view: View): Boolean {
        if (recyclerListener == null || value == null) {
            return false
        }
        recyclerListener?.onItemLongClick(this, value!!)
        return false
    }

}

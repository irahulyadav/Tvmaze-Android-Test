package com.magine.adapter

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.magine.R

import com.magine.listener.RecyclerViewListener
import com.magine.model.Show

import com.magine.setting.SortingSetting
import com.magine.view.RecycleViewHolder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.show_item_view.view.*

class ShowListAdapter(
    context: Context, @LayoutRes val itemLayout: Int = R.layout.show_item_view,
    listener: RecyclerViewListener<Show>?
) : RecyclerViewSearchAdapter<RecycleViewHolder<Show>, Show>(listener) {


    var setting: SortingSetting? = null

//    override fun sort() {
//        super.sort()
//        val setting = this.setting
//        if (setting != null) {
//            val ascending = setting.ascending
//            when (setting.sortBy) {
//                0 -> {
//                    Collections.sort(list, if (ascending) Category.NameAscending else Category.NameDescending)
//                }
//                1 -> {
//                    Collections.sort(list, if (ascending) Category.TotalAscending else Category.TotalDescending)
//                }
//
//                2 -> {
//                    Collections.sort(list, if (ascending) Category.DateAscending else Category.DateDescending)
//                }
//            }
//        }
//    }

    override fun isValid(item: Show, filter: String): Boolean {
        return true
    }

    override fun getItemView(parent: ViewGroup): RecycleViewHolder<Show> =
        ShowViewHolder.get(itemLayout, parent, listener)
}


class ShowViewHolder(itemView: View, recyclerListener: RecyclerViewListener<Show>?) :
    RecycleViewHolder<Show>(itemView, recyclerListener) {

    companion object {
        fun get(
            @LayoutRes layout: Int, parent: ViewGroup,
            recyclerListener: RecyclerViewListener<Show>?
        ): ShowViewHolder {
            return ShowViewHolder(
                LayoutInflater.from(parent.context).inflate(layout, parent, false),
                recyclerListener
            )
        }
    }

    private val mContentView: TextView = itemView.content
    private val icon: ImageView = itemView.icon
    override fun setItem(value: Show?) {
        super.setItem(value)
        if (value != null) {
            mContentView.text = value.name
            loadImage(value)
        }

    }

    fun loadImage(show: Show) {
        Picasso.get()
            .load(show.image?.medium)
            .placeholder(R.drawable.no_img)
            .into(icon, object : Callback {
                override fun onSuccess() {
                    icon.setImageBitmap((icon.drawable as BitmapDrawable).bitmap)
                }

                override fun onError(e: java.lang.Exception?) {

                }
            })
    }

    override fun toString(): String {
        return super.toString() + " '" + mContentView.text + "'"
    }
}

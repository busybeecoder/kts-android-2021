package com.bignerdranch.android.ktsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activities.view.*


class ActivitiesAdapterDelegate(
    private val onItemClick: (item: Activities) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, ActivitiesAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is Activities
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activities, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: Activities) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var currentItem: Activities? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: Activities) = with(containerView) {
            currentItem = item
            "distance: ${item.distance} meters".also { distance.text = it }
            name.text = item.name
            likes.text = item.likes.toString()
        }
    }

    override fun onBindViewHolder(item: Any, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.bind(item as Activities)
    }
}
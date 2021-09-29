package com.bignerdranch.android.ktsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.ktsapplication.databinding.TextPostBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.text_post.view.*

class PostTextDelegate() : AbsListItemAdapterDelegate<Any, Any, PostTextDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is PostText
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_post, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        item: Any,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item as PostText)
    }

    inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = TextPostBinding.bind(containerView)

        fun bind(postText: PostText) = with(binding) {
            avatarView.setImageResource(postText.userAvatar)
            userNameView.text = postText.userName
            tweetView.text = postText.tweet
        }
    }
}
package com.bignerdranch.android.ktsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.ktsapplication.databinding.ImagePostBinding
import kotlinx.android.extensions.LayoutContainer
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.image_post.view.*

class PostImageDelegate : AbsListItemAdapterDelegate<Any, Any, PostImageDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is PostImage
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_post, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as PostImage)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = ImagePostBinding.bind(containerView)

        fun bind(postImage: PostImage) = with(binding) {
            avatarImageView.avatarView.setImageResource(postImage.userAvatar)
            pictureView.setImageResource(postImage.picture)
            userNameView.text = postImage.userName
            likesCountView.text = postImage.likesCount.toString()
            likeButton.setOnClickListener {
                postImage.likesCount++
                likesCountView.text = postImage.likesCount.toString()
            }
        }
    }
}
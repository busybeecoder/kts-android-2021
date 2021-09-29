package com.bignerdranch.android.ktsapplication

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FeedDelegateListAdapter : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(PostImageDelegate())
            .addDelegate(PostTextDelegate())
            .addDelegate(PageLoadingDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is PostImage -> first.uuid == (second as PostImage).uuid
                is PostText -> first.uuid == (second as PostText).uuid
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }
}
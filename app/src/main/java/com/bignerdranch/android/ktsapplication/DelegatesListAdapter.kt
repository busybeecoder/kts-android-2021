package com.bignerdranch.android.ktsapplication

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.ktsapplication.Utils.PageLoadingDelegate
import com.bignerdranch.android.ktsapplication.api.Activities
import com.bignerdranch.android.ktsapplication.api.ActivitiesAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DelegatesListAdapter(
    detailedActivity: (item: Activities) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(ActivitiesAdapterDelegate(detailedActivity))
            .addDelegate(PageLoadingDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is Activities -> first.id == (second as Activities).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }
}
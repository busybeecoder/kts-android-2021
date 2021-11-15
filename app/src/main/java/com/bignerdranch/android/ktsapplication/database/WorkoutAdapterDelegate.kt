package com.bignerdranch.android.ktsapplication.database

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.ktsapplication.bindingInflate
import com.bignerdranch.android.ktsapplication.databinding.ActivitiesBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class WorkoutAdapterDelegate(
    private val onWorkoutClick: (Workout) -> Unit
) : AbsListItemAdapterDelegate<Workout, Workout, WorkoutAdapterDelegate.Holder>() {

    override fun isForViewType(item: Workout, items: MutableList<Workout>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return parent.bindingInflate(ActivitiesBinding::inflate).let {
            Holder(it, onWorkoutClick)
        }
    }

    override fun onBindViewHolder(item: Workout, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
        private val binding: ActivitiesBinding,
        onWorkoutClick: (Workout) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentWorkout: Workout? = null

        init {
            binding.root.setOnClickListener { currentWorkout?.let(onWorkoutClick) }
        }

        fun bind(item: Workout) = with(binding) {
            currentWorkout = item
            "distance: ${item.distance} meters".also { distance.text = it }
            name.text = item.name
            likes.text = item.kudos.toString()
        }
    }
}
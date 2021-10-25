package com.bignerdranch.android.ktsapplication

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class WorkoutListAdapter(
    onWorkoutClick: (Workout) -> Unit
) : AsyncListDifferDelegationAdapter<Workout>(WorkoutDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(WorkoutAdapterDelegate(onWorkoutClick))
    }

    class WorkoutDiffUtilCallback : DiffUtil.ItemCallback<Workout>() {
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem == newItem
        }
    }
}
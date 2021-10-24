package com.bignerdranch.android.ktsapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.ktsapplication.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val activitiesViewModel: ActivitiesViewModel by viewModels()
    private val dataViewModel: DataViewModel by activityViewModels()
    private val viewModelWorkout by viewModels<WorkoutViewModel>()
    private val workoutListViewModel by viewModels<WorkoutListViewModel>()
    private var activitiesAdapter: DelegatesListAdapter by autoCleared()
    private var workoutListAdapter: WorkoutListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        if (isOnline(requireContext())) {
            bindViewModel()
        } else {
            initListWorkout()
            bindViewModelWorkout()
        }
    }

    private fun initListWorkout() {
        workoutListAdapter = WorkoutListAdapter(::navigateTofullActivityFragment)
        with(binding.feed) {
            adapter = workoutListAdapter
            setHasFixedSize(true)
        }
    }

    private fun navigateTofullActivityFragment(workout: Workout) {
        findNavController().navigate(R.id.action_homeFragment_to_fullActivityFragment)
    }

    private fun initList() {
        activitiesAdapter = DelegatesListAdapter(
            detailedActivity = { activities ->
                Log.d("tag", "activity id = ${activities.id}")
                dataViewModel.activityId.value = activities.id
                findNavController().navigate(R.id.action_homeFragment_to_fullActivityFragment)
            }
        )
        binding.feed.apply {
            val orientation = RecyclerView.VERTICAL
            adapter = activitiesAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        activitiesViewModel.activitiesList.observe(
            viewLifecycleOwner,
            {
                activitiesAdapter.items = it
                for (item in it) {
                    viewModelWorkout.save(
                        item.id,
                        item.name,
                        item.distance,
                        item.likes
                    )
                }
            })
        activitiesViewModel.isLoading.observe(
            viewLifecycleOwner,
            { enableControls(it.not()) })
        activitiesViewModel.getListActivities()
    }

    private fun enableControls(enable: Boolean) = with(binding) {

    }

    private fun bindViewModelWorkout() {
        viewLifecycleOwner.launchOnStartedState {
            workoutListViewModel.workoutsFlow.collect {
                workoutListAdapter.items = it
            }
        }
    }
}

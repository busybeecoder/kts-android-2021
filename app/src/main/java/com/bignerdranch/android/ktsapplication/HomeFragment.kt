package com.bignerdranch.android.ktsapplication

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.ktsapplication.Utils.SharedPrefs
import com.bignerdranch.android.ktsapplication.Utils.autoCleared
import com.bignerdranch.android.ktsapplication.api.ActivitiesViewModel
import com.bignerdranch.android.ktsapplication.auth.AuthToken.token
import com.bignerdranch.android.ktsapplication.database.Workout
import com.bignerdranch.android.ktsapplication.database.WorkoutViewModel
import com.bignerdranch.android.ktsapplication.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val activitiesViewModel: ActivitiesViewModel by viewModels()
    private val dataViewModel: DataViewModel by activityViewModels()
    private val viewModelWorkout by viewModels<WorkoutViewModel>()
    private val workoutListViewModel by viewModels<WorkoutListViewModel>()
    private var activitiesAdapter: DelegatesListAdapter by autoCleared()
    private var workoutListAdapter: WorkoutListAdapter by autoCleared()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.exit -> {
            Log.d("Clicked", "EXit")
            findNavController().navigate(R.id.action_homeFragment_to_onboardingFragment)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isOnline(requireContext())) {
            initList()
            bindViewModel()
        } else {
            initListWorkout()
            bindViewModelWorkout()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Clicked", "EXit")
        token = null
    }

    private fun initListWorkout() {
        workoutListAdapter = WorkoutListAdapter(::navigateTofullActivityFragment)
        with(binding.feed) {
            val orientation = RecyclerView.VERTICAL
            adapter = workoutListAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun navigateTofullActivityFragment(workout: Workout) {
        findNavController().navigate(R.id.action_homeFragment_to_fullActivityFragment)
    }

    private fun initList() {
        activitiesAdapter = DelegatesListAdapter(
            detailedActivity = { activities ->
                Timber.d("activity id = " + activities.id)
                dataViewModel.activityId.value = activities.id
                findNavController().navigate(R.id.action_homeFragment_to_fullActivityFragment)
            }
        )
        with(binding.feed) {
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
                if (it == null) {
                    Toast.makeText(activity, "no activities", Toast.LENGTH_LONG).show()
                } else {
                    activitiesAdapter.items = it
                    for (item in it) {
                        viewModelWorkout.save(
                            item.id,
                            item.name,
                            item.distance,
                            item.likes
                        )
                    }
                }

            })
        activitiesViewModel.getListActivities()
    }

    private fun bindViewModelWorkout() {
        viewLifecycleOwner.lifecycleScope.launch {
            workoutListViewModel.workoutsFlow.collect {
                workoutListAdapter.items = it
            }
        }
    }
}

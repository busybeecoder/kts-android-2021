package com.bignerdranch.android.ktsapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.ktsapplication.api.FullActivityViewModel
import com.bignerdranch.android.ktsapplication.database.DetailedWorkoutViewModel
import com.bignerdranch.android.ktsapplication.databinding.ImagePostBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FullActivityFragment : Fragment(R.layout.image_post) {

    private val viewModel: FullActivityViewModel by viewModels()
    private val dataViewModel: DataViewModel by activityViewModels()
    private val viewModelDetailedWorkout: DetailedWorkoutViewModel by viewModels()
    private val binding by viewBinding(ImagePostBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataViewModel.activityId.value?.let {
            viewModel.getActivityById(
                id = it,
                include_all_efforts = true
            )
        }
        if (isOnline(requireContext())) {
            bindViewModel()
        } else {
            dataViewModel.activityId.value?.let { viewModelDetailedWorkout.loadWorkoutById(it) }
            bindViewModelDetailedWorkout()
        }
    }

    private fun bindViewModelDetailedWorkout() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModelDetailedWorkout.workoutFlow.collect { value ->
                binding.name.text = value?.name.toString()
                binding.distance.text = value?.distance.toString() + " m"
                binding.time.text = value?.time.toString() + " s"
                binding.avgSpeed.text = value?.avgSpeed.toString() + "m/s"
            }
        }
    }

    private fun bindViewModel() {
        viewModel.fullActivity.observe(viewLifecycleOwner, Observer {
            binding.name.text = it?.name.toString()
            binding.distance.text = it?.distance.toString() + " m"
            binding.time.text = it?.time.toString() + " s"
            binding.avgSpeed.text = it?.avgSpeed.toString() + "m/s"

            Glide.with(this)
                .load("https://lh3.googleusercontent.com/a-/AOh14Gj1toTTS7TbybLgcI3o_FnJWS5VGKf2nGf28VZjgg=s96-c")
                .into(binding.pictureView)
            if (it != null) {
                viewModelDetailedWorkout.save(dataViewModel.activityId.value!!, fullActivity = it)
            }
        })
    }
}

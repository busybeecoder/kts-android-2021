package com.bignerdranch.android.ktsapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.ktsapplication.databinding.ImagePostBinding
import com.bumptech.glide.Glide

class FullActivityFragment : Fragment(R.layout.image_post) {

    private val viewModel: FullActivityViewModel by viewModels()
    private val dataViewModel: DataViewModel by activityViewModels()

    private val binding by viewBinding(ImagePostBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataViewModel.activityId.value?.let {
            viewModel.getActivityById(
                id = it,
                include_all_efforts = true
            )
        }
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.detailedActivity.observe(viewLifecycleOwner, Observer {
            binding.name.text = it?.name.toString()
            binding.distance.text = it?.distance.toString() + " m"
            binding.time.text = it?.time.toString() + " s"
            binding.avgSpeed.text = it?.avgSpeed.toString() + "m/s"

            Glide.with(this)
                .load("https://lh3.googleusercontent.com/a-/AOh14Gj1toTTS7TbybLgcI3o_FnJWS5VGKf2nGf28VZjgg=s96-c")
                .into(binding.pictureView)
        })
    }

    companion object {
        private const val ACTIVITY_ID = 6094731042
    }
}

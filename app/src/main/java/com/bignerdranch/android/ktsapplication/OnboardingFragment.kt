package com.bignerdranch.android.ktsapplication

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.ktsapplication.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val binding: FragmentOnboardingBinding by viewBinding(FragmentOnboardingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences =
            requireContext().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("Finished", false)) {
            findNavController().navigate(R.id.action_onboardingFragment_to_authFragment)
        }
        val button = binding.startButton
        button.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_authFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
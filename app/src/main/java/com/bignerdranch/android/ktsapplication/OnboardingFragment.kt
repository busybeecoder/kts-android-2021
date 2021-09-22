package com.bignerdranch.android.ktsapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import timber.log.Timber

class OnboardingFragment : Fragment() {

    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("OnCreateView ${hashCode()}")
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        button = view.findViewById(R.id.start_button) as Button
        return view
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart ${hashCode()}")
        button.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView")
    }
}
package com.example.gsport24.ui.screens.authorization.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.gsport24.databinding.FragmentAuthTypeBinding
import com.example.gsport24.mvvm.ui.BaseFragment

class AuthTypeFragment : BaseFragment<FragmentAuthTypeBinding>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentAuthTypeBinding
		get() = FragmentAuthTypeBinding::inflate

	override fun initView(binding : FragmentAuthTypeBinding) {
		binding.tvSignIn.setOnClickListener {
			val extra = FragmentNavigatorExtras(
				binding.appIcon to binding.appIcon.transitionName,
				binding.tvSignIn to binding.tvSignIn.transitionName
			)
			findNavController().navigate(AuthTypeFragmentDirections.actionAuthTypeFragmentToSignInFragment(), extra)
		}
		binding.tvSignUp.setOnClickListener {
			val extra = FragmentNavigatorExtras(
				binding.appIcon to binding.appIcon.transitionName
			)
			findNavController().navigate(AuthTypeFragmentDirections.actionAuthTypeFragmentToNavGraphSignUp() , extra)
		}
	}

}
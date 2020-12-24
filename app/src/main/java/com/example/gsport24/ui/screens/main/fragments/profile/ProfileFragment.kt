package com.example.gsport24.ui.screens.main.fragments.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gsport24.databinding.FragmentProfileBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand

class ProfileFragment : BaseRequestFragment<FragmentProfileBinding, ProfileViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
		get() = FragmentProfileBinding::inflate

	override val viewModelType : Class<ProfileViewModel>
		get() = ProfileViewModel::class.java

	override fun initView(binding : FragmentProfileBinding, viewModel : ProfileViewModel) {
		binding.apply {
			lifecycleOwner = this@ProfileFragment
			setViewModel(viewModel)
			tvProfile.setOnClickListener {
				findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToUserInfoFragment())
			}
			tvBalance.setOnClickListener {
				findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToBalanceFragment())
			}
			tvBids.setOnClickListener { }
			tvSettings.setOnClickListener { }
			tvTerms.setOnClickListener { }
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}
}
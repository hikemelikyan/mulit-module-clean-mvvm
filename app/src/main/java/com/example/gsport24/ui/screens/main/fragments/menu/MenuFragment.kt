package com.example.gsport24.ui.screens.main.fragments.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gsport24.databinding.FragmentMenuBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand

class MenuFragment : BaseRequestFragment<FragmentMenuBinding, MenuViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentMenuBinding
		get() = FragmentMenuBinding::inflate

	override val viewModelType : Class<MenuViewModel>
		get() = MenuViewModel::class.java

	override fun initView(binding : FragmentMenuBinding, viewModel : MenuViewModel) {
		binding.apply {
			news.root.setOnClickListener {
				findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToNewsFragment())
			}
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}
}
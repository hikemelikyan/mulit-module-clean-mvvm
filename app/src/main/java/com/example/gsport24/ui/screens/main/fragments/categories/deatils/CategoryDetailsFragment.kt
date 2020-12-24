package com.example.gsport24.ui.screens.main.fragments.categories.deatils

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.FragmentCategoryDetailsBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand

class CategoryDetailsFragment : BaseRequestFragment<FragmentCategoryDetailsBinding, CategoryDetailsViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryDetailsBinding
		get() = FragmentCategoryDetailsBinding::inflate

	override val viewModelType : Class<CategoryDetailsViewModel>
		get() = CategoryDetailsViewModel::class.java

	override fun initView(binding : FragmentCategoryDetailsBinding, viewModel : CategoryDetailsViewModel) {

	}

	override fun proceedViewCommand(command : ViewCommand) {

	}
}
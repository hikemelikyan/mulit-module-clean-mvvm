package com.example.gsport24.ui.screens.main.fragments.categories.categoriesList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.R
import com.example.gsport24.databinding.FragmentCategoryListBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.ui.StateLayout
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.adapters.CategoriesAdapter

class CategoryListFragment : BaseRequestFragment<FragmentCategoryListBinding, CategoryListViewModel>() {

	override val hasMainRequest : Boolean
		get() = true

	override val hasEmptyState : Pair<Boolean, StateLayout.EmptyModel?>
		get() = Pair(true, StateLayout.EmptyModel(R.drawable.background, R.string.category_empty_title, R.string.category_empty_subtitle))

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryListBinding
		get() = FragmentCategoryListBinding::inflate

	override val viewModelType : Class<CategoryListViewModel>
		get() = CategoryListViewModel::class.java

	override fun initView(binding : FragmentCategoryListBinding, viewModel : CategoryListViewModel) {
		binding.apply {
			setViewModel(viewModel)
			rvCategories.adapter = CategoriesAdapter {

			}
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}
}
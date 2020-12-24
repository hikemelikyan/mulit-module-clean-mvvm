package com.example.gsport24.ui.screens.main.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import com.example.gsport24.R
import com.example.gsport24.databinding.FragmentHomeBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.ui.TitleAdapter
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.main.fragments.home.adapters.CategoriesSeeAllAdapter
import com.example.gsport24.ui.screens.main.fragments.home.adapters.HomeCategoriesAdapter
import com.example.gsport24.ui.screens.main.fragments.home.adapters.HomePagerAdapter
import com.example.gsport24.ui.screens.main.fragments.home.adapters.NewsMainListAdapter

class HomeFragment : BaseRequestFragment<FragmentHomeBinding, HomeViewModel>() {

	private val newsAdapter : NewsMainListAdapter by lazy { NewsMainListAdapter {} }
	private val bannerAdapter : HomePagerAdapter by lazy { HomePagerAdapter() }
	private val categoryAdapter : HomeCategoriesAdapter by lazy { HomeCategoriesAdapter() }

	override val hasMainRequest : Boolean
		get() = true

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
		get() = FragmentHomeBinding::inflate

	override val viewModelType : Class<HomeViewModel>
		get() = HomeViewModel::class.java

	override fun initView(binding : FragmentHomeBinding, viewModel : HomeViewModel) {
		binding.apply {
			setViewModel(viewModel)
			rvHome.adapter = ConcatAdapter(
				bannerAdapter,
				TitleAdapter(R.string.popular_categories),
				categoryAdapter,
				CategoriesSeeAllAdapter(::onSeeAllClick),
				TitleAdapter(R.string.latest_news, R.string.all),
				newsAdapter
			)
		}
	}

	private fun onSeeAllClick(){
		showToast("See all")
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}
}
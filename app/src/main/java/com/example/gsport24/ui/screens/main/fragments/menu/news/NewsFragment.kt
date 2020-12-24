package com.example.gsport24.ui.screens.main.fragments.menu.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gsport24.databinding.FragmentNewsBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.main.fragments.home.adapters.NewsListAdapter
import com.google.gson.Gson

class NewsFragment : BaseRequestFragment<FragmentNewsBinding, NewsViewModel>() {

	override val viewModelType : Class<NewsViewModel>
		get() = NewsViewModel::class.java

	override fun initView(binding : FragmentNewsBinding, viewModel : NewsViewModel) {
		binding.apply {
			setViewModel(viewModel)
			viewModel.getList()
			rvNews.adapter = NewsListAdapter {
				findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(Gson().toJson(it)))
			}
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}

	override val hasMainRequest : Boolean
		get() = true
	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsBinding
		get() = FragmentNewsBinding::inflate

}
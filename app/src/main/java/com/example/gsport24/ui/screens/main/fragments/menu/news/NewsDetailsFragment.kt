package com.example.gsport24.ui.screens.main.fragments.menu.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.gsport24.databinding.FragmentNewsDetailsBinding
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.main.fragments.menu.news.adapter.NewsPhotosAdapter
import com.google.gson.Gson

class NewsDetailsFragment : BaseRequestFragment<FragmentNewsDetailsBinding, NewsViewModel>() {

	private val mArgs : NewsDetailsFragmentArgs by navArgs()

	override val viewModelType : Class<NewsViewModel>
		get() = NewsViewModel::class.java

	override fun initView(binding : FragmentNewsDetailsBinding, viewModel : NewsViewModel) {
		binding.apply {
			lifecycleOwner = viewLifecycleOwner
			setViewModel(viewModel)
			initViewPager(binding)
			val newsItem = Gson().fromJson(mArgs.newsItem, NewsDomain::class.java)
			viewModel.getDetails(newsItem.id)
		}
	}

	override val hasMainRequest : Boolean
		get() = true

	private fun initViewPager(binding : FragmentNewsDetailsBinding) {
		binding.apply {
			vpPhotos.adapter = NewsPhotosAdapter()
			vpPhotos.orientation = ViewPager2.ORIENTATION_VERTICAL
			dotsIndicator.setViewPager2(binding.vpPhotos)
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsDetailsBinding
		get() = FragmentNewsDetailsBinding::inflate

}
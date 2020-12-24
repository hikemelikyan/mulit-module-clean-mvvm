package com.example.gsport24.ui.screens.main.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.domain.useCase.home.GetHomeInfoUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.screens.main.fragments.home.di.HomeFragmentComponent
import javax.inject.Inject

class HomeViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase : GetHomeInfoUseCase

	private val _news : MutableLiveData<List<NewsDomain>> by lazy { MutableLiveData() }
	val news : LiveData<List<NewsDomain>>
		get() = _news

	private val _banners : MutableLiveData<List<String>> by lazy { MutableLiveData() }
	val banners : LiveData<List<String>>
		get() = _banners

	private val _categories: MutableLiveData<List<CategoryDomain>> by lazy { MutableLiveData() }
	val categories : LiveData<List<CategoryDomain>>
		get() = _categories

	init {
		inject()
		getList()
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<HomeFragmentComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<HomeFragmentComponent>()
	}

	private fun getList() {
		launchDefault {
			val banners = useCase.getBannersList()
			banners doOnSuccess  {
				_banners.postValue(it)
			}
			val categories = useCase.getCategoriesList()
			categories doOnSuccess {
				_categories.postValue(it)
			}
			val news = useCase.getNewsList()
			news doOnSuccess  {
				_news.postValue(it)
			}
		}
	}
}
package com.example.gsport24.ui.screens.main.fragments.menu.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.gsport24.Application
import com.example.gsport24.data.model.responseModels.NewsDetailsResponseModel
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.domain.useCase.menu.GetMenuUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.screens.main.fragments.menu.di.NewsFragmentComponent
import javax.inject.Inject

class NewsViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase : GetMenuUseCase

	private val _news : MutableLiveData<PagingData<NewsDomain>> by lazy { MutableLiveData() }
	val news : LiveData<PagingData<NewsDomain>>
		get() = _news


	private val _details : MutableLiveData<NewsDetailsResponseModel> by lazy { MutableLiveData() }
	val details : LiveData<NewsDetailsResponseModel>
		get() = _details

	init {
		inject()
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<NewsFragmentComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<NewsFragmentComponent>()
	}

	fun getList() {
		launchDefault {
			val news = useCase.getNewsList()
			news doOnSuccess {
				_news.postValue(it)
			}
		}
	}

	fun getDetails(id : Int) {
		launchDefault {
			val news = useCase.getDetails(id)
			news doOnSuccess {
				Log.i("NewsTag",it.toString())
				_details.postValue(it)
			}
		}
	}
}
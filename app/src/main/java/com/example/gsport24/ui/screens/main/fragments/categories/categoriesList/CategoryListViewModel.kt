package com.example.gsport24.ui.screens.main.fragments.categories.categoriesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.domain.useCase.category.GetAllCategoriesUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.di.CategoryPageComponent
import javax.inject.Inject

class CategoryListViewModel : BaseViewModel() {

	init {
		inject()
		getAll()
	}

	private val _categories : MutableLiveData<List<CategoryDomain>> by lazy { MutableLiveData() }
	val categories : LiveData<List<CategoryDomain>>
		get() = _categories

	override fun inject() {
		Application.getInstance().injectionStorage.get<CategoryPageComponent>().inject(this)
	}

	override fun releaseInjection() {
		TODO("Not yet implemented")
	}

	@Inject
	lateinit var useCase : GetAllCategoriesUseCase

	private fun getAll() {
		launchDefault {
			useCase.getAll() doOnSuccess  {
				_categories.postValue(it)
			}
		}
	}

}
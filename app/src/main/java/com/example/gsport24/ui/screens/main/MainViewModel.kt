package com.example.gsport24.ui.screens.main

import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.domain.useCase.profile.get.GetUserPersonalInformationUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.root.ext.format
import com.example.gsport24.ui.screens.main.di.MainComponent
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

	@Inject
	lateinit var userPersonalInformationUseCase : GetUserPersonalInformationUseCase

	private val _userBalance : MutableLiveData<String> by lazy { MutableLiveData() }
	val userBalance : LiveData<String>
		get() = _userBalance

	private val _balanceVisibilityFilter : MutableLiveData<MaskFilter> by lazy { MutableLiveData() }
	val balanceVisibilityFilter : LiveData<MaskFilter>
		get() = _balanceVisibilityFilter

	private val filter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)

	init {
		inject()
		getUserInfo()
		_balanceVisibilityFilter.postValue(filter)
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<MainComponent>().inject(this)
	}

	private fun getUserInfo() {
		launchDefault {
			val result = userPersonalInformationUseCase.getUserInfo()
			result doOnSuccess {
				Application.getInstance().updateUserInfo(it)
				_userBalance.postValue(it.balance.format())
			}
		}
	}

	fun hideBalance() {
		_balanceVisibilityFilter.postValue(filter)
	}

	fun showBalance() {
		_balanceVisibilityFilter.postValue(null)
	}

	fun isBalanceHidden() : Boolean {
		return _balanceVisibilityFilter.value == filter
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<MainComponent>()
	}
}
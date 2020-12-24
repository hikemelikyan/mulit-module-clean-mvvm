package com.example.gsport24.ui.screens.main.fragments.profile

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.R
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.domain.useCase.profile.get.GetUserPersonalInformationUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.root.di.ApplicationContext
import com.example.gsport24.root.ext.getColorCompat
import com.example.gsport24.ui.screens.main.fragments.profile.di.ProfileTabComponent
import javax.inject.Inject

class ProfileViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase : GetUserPersonalInformationUseCase

	@Inject
	@ApplicationContext
	lateinit var app : Context

	init {
		inject()
		initUserInfo()
	}

	private val _userName : MutableLiveData<String> by lazy { MutableLiveData() }
	val userName : LiveData<String>
		get() = _userName

	private val _userStatus : MutableLiveData<CharSequence> by lazy { MutableLiveData() }
	val userStatus : LiveData<CharSequence>
		get() = _userStatus

	private fun initUserInfo() {
		launchIO {
			val result = useCase.getUserInfoInternal()
			result doOnSuccess {
				val info : UserDomain? = Application.getInstance().getUserInfo()
				_userName.postValue("${info?.firstName} ${info?.lastName}")
				val span = SpannableString(if (info?.isUserConfirmed == true) app.getString(R.string.status_confirmed) else app.getString(R.string.status_unconfirmed))
				span.setSpan(
					ForegroundColorSpan(
						if (info?.isUserConfirmed == true) {
							app.getColorCompat(R.color.colorAccent)
						} else {
							app.getColorCompat(R.color.pickled_radish)
						}
					), 0, span.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
				)
				_userStatus.postValue(span)
			}
		}
	}

	fun getUserInfo() : UserDomain? = Application.getInstance().getUserInfo()

	override fun inject() {
		Application.getInstance().injectionStorage.get<ProfileTabComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<ProfileTabComponent>()
	}
}
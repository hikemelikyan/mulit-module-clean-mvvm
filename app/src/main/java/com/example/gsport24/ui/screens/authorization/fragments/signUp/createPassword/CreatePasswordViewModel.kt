package com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword

import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.data.model.requestModels.ReqModelRegister
import com.example.gsport24.domain.useCase.userRegistration.UserRegistrationUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.root.utils.SharedPreferencesHelper
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword.di.CreatePasswordComponent
import com.google.firebase.installations.FirebaseInstallations
import javax.inject.Inject

class CreatePasswordViewModel : BaseViewModel() {

	@Inject
	lateinit var userRegistrationUseCase : UserRegistrationUseCase

	@Inject
	lateinit var mShared : SharedPreferencesHelper

	val password = MutableLiveData<String>()
	val password2 = MutableLiveData<String>()

	init {
		inject()
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<CreatePasswordComponent>().inject(this)

	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<CreatePasswordComponent>()
	}

	fun registerUser(model : ReqModelRegister) {
		model.apply {
			deviceId = mShared.getDeviceId()
		}
		mShared.getFirebaseToken()?.let {
			registerInternal(model.apply {
				deviceToken = it
			})
		} ?: FirebaseInstallations.getInstance().getToken(false).addOnSuccessListener {
			mShared.saveFirebaseToken(it.token)
			registerInternal(model.apply {
				deviceToken = it.token
			})
		}
	}

	private fun registerInternal(model : ReqModelRegister) {
		launchDefault {
			val result = userRegistrationUseCase.registerUser(model)
			result doOnSuccess {
				mShared.saveAuthToken(it)
				_viewCommands.postValue(Commands.NavigateToMain)
			}
		}
	}

}
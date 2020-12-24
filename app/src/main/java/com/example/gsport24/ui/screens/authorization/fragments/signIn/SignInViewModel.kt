package com.example.gsport24.ui.screens.authorization.fragments.signIn

import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.data.model.requestModels.ReqModelLogin
import com.example.gsport24.data.root.UIState
import com.example.gsport24.domain.useCase.login.LogInUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.root.utils.SharedPreferencesHelper
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.authorization.fragments.signIn.di.SignInComponent
import com.google.firebase.installations.FirebaseInstallations
import javax.inject.Inject

class SignInViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase : LogInUseCase

	@Inject
	lateinit var mShared : SharedPreferencesHelper

	val userName = MutableLiveData<String>()
	val password = MutableLiveData<String>()

	init {
		inject()
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<SignInComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<SignInComponent>()
	}

	fun logIn() {
		switchUIState(UIState.LOADING)
		mShared.getFirebaseToken()?.let {
			logInInternal(it)
		} ?: FirebaseInstallations.getInstance().getToken(false).addOnSuccessListener {
			mShared.saveFirebaseToken(it.token)
			logInInternal(it.token)
		}
	}

	private fun logInInternal(token : String) {
		launchDefault {
			val result = useCase.logIn(
				ReqModelLogin(
					userName.value.toString(),
					password.value.toString(),
					mShared.getDeviceId() ?: "",
					token
				)
			)
			result doOnSuccess {
				mShared.saveAuthToken(it)
				_viewCommands.postValue(Commands.NavigateToMain)
			}
		}
	}
}
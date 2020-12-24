package com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword

import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.domain.useCase.profile.changePassword.ChangePasswordUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword.di.ChangePasswordComponent
import javax.inject.Inject

class ChangePasswordViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase:ChangePasswordUseCase

	val oldPassword:MutableLiveData<String> by lazy { MutableLiveData() }
	val newPassword:MutableLiveData<String> by lazy { MutableLiveData() }
	val confirmPassword:MutableLiveData<String> by lazy { MutableLiveData() }

	init {
		inject()
	}

	override fun inject() {
		Application.getInstance()
			.injectionStorage
			.get<ChangePasswordComponent>()
			.inject(this)
	}

	fun changePassword(){
		launchDefault {
			val result = useCase.changePassword(oldPassword.value.toString(),confirmPassword.value.toString())
			result doOnSuccess {
				_viewCommands.postValue(Commands.PasswordUpdated)
			}
		}
	}

	override fun releaseInjection() {
		Application.getInstance()
			.injectionStorage
			.release<ChangePasswordComponent>()
	}
}
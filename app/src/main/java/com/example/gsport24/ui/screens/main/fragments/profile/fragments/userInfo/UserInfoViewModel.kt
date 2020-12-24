package com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo

import com.example.gsport24.Application
import com.example.gsport24.data.model.requestModels.ReqModelChangeUserInfo
import com.example.gsport24.domain.useCase.profile.update.UpdateUserInfoUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo.di.UserInfoComponent
import javax.inject.Inject

class UserInfoViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase : UpdateUserInfoUseCase

	init {
		inject()
	}

	fun updateUserInfo(model : ReqModelChangeUserInfo) {
		launchDefault {
			val result = useCase.updateUserInfo(model)
			result doOnSuccess {
				_viewCommands.postValue(Commands.UserInfoUpdated)
			}
		}
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<UserInfoComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<UserInfoComponent>()
	}
}
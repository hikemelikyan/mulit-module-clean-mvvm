package com.example.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification

import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.domain.useCase.phoneVerification.PhoneVerificationUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.di.PhoneVerificationComponent
import javax.inject.Inject

class PhoneVerificationViewModel : BaseViewModel() {

	@Inject
	lateinit var verificationUseCase : PhoneVerificationUseCase

	val phoneNumber = MutableLiveData<String>()
	val phoneCode = MutableLiveData<String>()

	val codeId = MutableLiveData<Int>()

	init {
		inject()
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<PhoneVerificationComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<PhoneVerificationComponent>()
	}

	fun getSmsCode() {
		launchDefault {
			phoneNumber.value?.let {
				val result = verificationUseCase.getVerificationSmsCode(ReqModelRegisterPhone(it))
				result doOnSuccess {
					_viewCommands.postValue(Commands.CodeSent(it.id))
					codeId.postValue(it.id)
				}
			}
		}
	}

	fun verifyPhoneNumber() {
		launchDefault {
			phoneCode.value?.let {
				val result = verificationUseCase.verifyPhone(ReqModelVerifyPhoneOrEmail(codeId.value ?: 0, it))
				result doOnSuccess {
					_viewCommands.postValue(Commands.PhoneVerified(codeId.value ?: 0 ))
				}
			}
		}
	}
}
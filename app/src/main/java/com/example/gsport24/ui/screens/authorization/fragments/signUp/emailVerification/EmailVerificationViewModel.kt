package com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification

import androidx.lifecycle.MutableLiveData
import com.example.gsport24.Application
import com.example.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.domain.useCase.emailVerification.EmailVerificationUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.di.EmailVerificationComponent
import javax.inject.Inject

class EmailVerificationViewModel : BaseViewModel() {

	@Inject
	lateinit var emailAuthUseCase : EmailVerificationUseCase

	val email = MutableLiveData<String>()
	val code = MutableLiveData<String>()

	private val _codeId = MutableLiveData<Int>()

	val isCodeSent = MutableLiveData<Boolean>()

	init {
		inject()
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<EmailVerificationComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<EmailVerificationComponent>()
	}

	fun getSmsCodeByEmail() {
		launchDefault {
			email.value?.let {
				val result = emailAuthUseCase.getVerificationCodeByEmail(ReqModelRegisterEmail(it))
				result doOnSuccess {
					_codeId.postValue(it.id)
					_viewCommands.postValue(Commands.CodeSent(it.id))
					isCodeSent.postValue(true)
				}
			}
		}
	}

	fun verifyEmail() {
		launchDefault {
			code.value?.let {
				val result = emailAuthUseCase.verifyEmail(ReqModelVerifyPhoneOrEmail(_codeId.value ?: 0, it))
				result doOnSuccess {
					_viewCommands.postValue(Commands.EmailVerified(_codeId.value ?: 0))
				}
			}
		}
	}
}
package com.example.gsport24.domain.useCase.phoneVerification

import com.example.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.data.repository.auth.AuthRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.SmsCodeIdDomain
import com.example.gsport24.domain.mappers.VerificationCodeIdMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhoneVerificationUseCaseImpl
@Inject
constructor(
	private val authRepository : AuthRepository,
	private val resultFactory : ResultFactory
) : PhoneVerificationUseCase {

	override suspend fun getVerificationSmsCode(requestModel : ReqModelRegisterPhone) : Flow<Result<SmsCodeIdDomain>> {
		return resultFactory.getResult { authRepository.getSmsCode(requestModel,VerificationCodeIdMapper) }
	}

	override suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>> {
		return resultFactory.getResult { authRepository.verifyPhone(model) }
	}
}
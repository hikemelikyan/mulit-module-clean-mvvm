package com.example.gsport24.domain.useCase.emailVerification

import com.example.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.data.repository.auth.AuthRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.SmsCodeIdDomain
import com.example.gsport24.domain.mappers.VerificationCodeIdMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailVerificationUseCaseImpl
@Inject
constructor(
	private val authRepository : AuthRepository,
	private val resultFactory : ResultFactory
) : EmailVerificationUseCase {

	override suspend fun getVerificationCodeByEmail(model:ReqModelRegisterEmail) : Flow<Result<SmsCodeIdDomain>> {
		return resultFactory.getResult { authRepository.getSmsCodeByEmail(model,VerificationCodeIdMapper) }
	}

	override suspend fun verifyEmail(model: ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>> {
		return resultFactory.getResult { authRepository.verifyEmail(model) }
	}
}
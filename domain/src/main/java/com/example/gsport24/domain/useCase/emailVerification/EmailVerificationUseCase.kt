package com.example.gsport24.domain.useCase.emailVerification

import com.example.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.SmsCodeIdDomain
import kotlinx.coroutines.flow.Flow

interface EmailVerificationUseCase {

	suspend fun getVerificationCodeByEmail(model:ReqModelRegisterEmail) : Flow<Result<SmsCodeIdDomain>>

	suspend fun verifyEmail(model:ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>>

}
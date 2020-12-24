package com.example.gsport24.domain.useCase.phoneVerification

import com.example.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.data.root.ApiException
import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.SmsCodeIdDomain
import kotlinx.coroutines.flow.Flow

interface PhoneVerificationUseCase {

	@Throws(ApiException::class)
	suspend fun getVerificationSmsCode(requestModel : ReqModelRegisterPhone) : Flow<Result<SmsCodeIdDomain>>

	@Throws(ApiException::class)
	suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Flow<Result<Boolean>>

}
package com.example.gsport24.data.repository.auth

import com.example.gsport24.data.model.requestModels.ReqModelLogin
import com.example.gsport24.data.model.requestModels.ReqModelRegister
import com.example.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.example.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.data.root.Entity
import com.example.gsport24.data.root.NetworkHelper
import com.example.gsport24.data.services.IAuthorizationService
import com.example.gsport24.root.mapperBase.Mapper
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(
	private val networkHelper : NetworkHelper,
	retrofit : Retrofit
) : AuthRepository {

	private val mService : IAuthorizationService = retrofit.create()

	override suspend fun <T : Entity> getSmsCode(model : ReqModelRegisterPhone, mapper : Mapper<Int?, T>) : T {
		return mapper.invoke(networkHelper.proceed { mService.getSmsCode(model) })
	}

	override suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Boolean? {
		return networkHelper.proceed { mService.verifyPhoneNumber(model) }
	}

	override suspend fun <T : Entity> getSmsCodeByEmail(model : ReqModelRegisterEmail, mapper : Mapper<Int?, T>) : T {
		return mapper.invoke(networkHelper.proceed { mService.getSmsByEmail(model) })
	}

	override suspend fun verifyEmail(model : ReqModelVerifyPhoneOrEmail) : Boolean? {
		return networkHelper.proceed { mService.verifyEmail(model) }
	}

	override suspend fun registerUser(model : ReqModelRegister) : String? {
		return networkHelper.proceed { mService.registerUser(model) }
	}

	override suspend fun login(model : ReqModelLogin) : String? {
		return networkHelper.proceed { mService.login(model) }
	}
}
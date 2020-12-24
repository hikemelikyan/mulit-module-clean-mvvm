package com.example.gsport24.data.repository.auth

import com.example.gsport24.data.model.requestModels.ReqModelLogin
import com.example.gsport24.data.model.requestModels.ReqModelRegister
import com.example.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.example.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.mapperBase.Mapper

interface AuthRepository {

	suspend fun <T : Entity> getSmsCode(model : ReqModelRegisterPhone, mapper : Mapper<Int?, T>) : T

	suspend fun verifyPhone(model : ReqModelVerifyPhoneOrEmail) : Boolean?

	suspend fun <T : Entity> getSmsCodeByEmail(model : ReqModelRegisterEmail,mapper : Mapper<Int?, T>) : T

	suspend fun verifyEmail(model : ReqModelVerifyPhoneOrEmail) : Boolean?

	suspend fun registerUser(model : ReqModelRegister) : String?

	suspend fun login(model : ReqModelLogin) : String?

}
package com.example.gsport24.data.services

import com.example.gsport24.data.model.requestModels.ReqModelLogin
import com.example.gsport24.data.model.requestModels.ReqModelRegister
import com.example.gsport24.data.model.requestModels.ReqModelRegisterEmail
import com.example.gsport24.data.model.requestModels.ReqModelRegisterPhone
import com.example.gsport24.data.model.requestModels.ReqModelVerifyPhoneOrEmail
import com.example.gsport24.data.model.responseModels.root.Response
import com.example.gsport24.data.root.IBaseRetrofitService
import retrofit2.http.Body
import retrofit2.http.POST

internal interface IAuthorizationService : IBaseRetrofitService {

	@POST("api/User/RegisterPhone")
	suspend fun getSmsCode(@Body model : ReqModelRegisterPhone) : Response<Int>

	@POST("api/User/VerifyPhone")
	suspend fun verifyPhoneNumber(@Body model : ReqModelVerifyPhoneOrEmail) : Response<Boolean>

	@POST("api/User/RegisterEmail")
	suspend fun getSmsByEmail(@Body model : ReqModelRegisterEmail) : Response<Int>

	@POST("api/User/VerifyEmail")
	suspend fun verifyEmail(@Body model : ReqModelVerifyPhoneOrEmail) : Response<Boolean>

	@POST("api/User/Register")
	suspend fun registerUser(@Body model : ReqModelRegister) : Response<String>

	@POST("api/User/Login")
	suspend fun login(@Body model : ReqModelLogin) : Response<String>

}
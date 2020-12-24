package com.example.gsport24.data.services

import com.example.gsport24.data.model.requestModels.ReqModelChangePassword
import com.example.gsport24.data.model.requestModels.ReqModelChangeUserInfo
import com.example.gsport24.data.model.responseModels.UserInfoResponseModel
import com.example.gsport24.data.model.responseModels.root.Response
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface IUserService {

	@GET("/api/User/Profile")
	suspend fun getProfileInfo() : Response<UserInfoResponseModel>

	@PUT("/api/User/ChangeInfo")
	suspend fun changeUserInfo(@Body model : ReqModelChangeUserInfo) : Response<Any>

	@Multipart
	@PUT("/api/User/ChangePassport")
	suspend fun changePassport(@Part multipart : List<MultipartBody.Part>) : Response<Any>

	@PUT("/api/User/ChangePassword")
	suspend fun changePassword(@Body model : ReqModelChangePassword) : Response<Boolean>

}
package com.example.gsport24.data.repository.user

import com.example.gsport24.data.model.requestModels.ReqModelChangePassword
import com.example.gsport24.data.model.requestModels.ReqModelChangeUserInfo
import com.example.gsport24.data.model.responseModels.UserInfoResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.mapperBase.Mapper
import okhttp3.MultipartBody

interface UserRepository {

	suspend fun <T : Entity> getProfileInfo(mapper : Mapper<UserInfoResponseModel, T>) : T?

	suspend fun getProfileInfoInternal() : String?

	suspend fun <T : Entity> updatePersonalInfo(model : ReqModelChangeUserInfo, mapper : Mapper<UserInfoResponseModel, T>) : T?

	suspend fun <T : Entity> changePassport(body : List<MultipartBody.Part>, mapper : Mapper<UserInfoResponseModel, T>) : T?

	suspend fun changePassword(model: ReqModelChangePassword) : Boolean?

}
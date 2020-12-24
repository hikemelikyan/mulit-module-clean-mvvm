package com.example.gsport24.data.repository.user

import com.example.gsport24.data.model.requestModels.ReqModelChangePassword
import com.example.gsport24.data.model.requestModels.ReqModelChangeUserInfo
import com.example.gsport24.data.model.responseModels.UserInfoResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.data.root.NetworkHelper
import com.example.gsport24.data.services.IUserService
import com.example.gsport24.root.mapperBase.Mapper
import com.example.gsport24.root.utils.SharedPreferencesHelper
import com.google.gson.Gson
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class UserRepositoryImpl
@Inject
constructor(
	private val networkHelper : NetworkHelper,
	private val mShared:SharedPreferencesHelper,
	retrofit : Retrofit
) : UserRepository {

	private val mService: IUserService = retrofit.create()

	override suspend fun <T : Entity> getProfileInfo(mapper : Mapper<UserInfoResponseModel, T>) : T? {
		return with(networkHelper.proceed { mService.getProfileInfo() }){
			this?.let {
				val entity = mapper(it)
				mShared.saveUserInfo(Gson().toJson(entity))
				entity
			}
		}
	}

	override suspend fun getProfileInfoInternal() : String? {
		return mShared.getUserInfo()
	}

	override suspend fun <T : Entity> updatePersonalInfo(model:ReqModelChangeUserInfo, mapper : Mapper<UserInfoResponseModel, T>) : T? {
		val updateResult = networkHelper.proceed { mService.changeUserInfo(model) }
		return updateResult?.let {
			getProfileInfo(mapper)
		}
	}

	override suspend fun <T : Entity> changePassport(body : List<MultipartBody.Part>, mapper : Mapper<UserInfoResponseModel, T>) : T?{
		val updateResult = networkHelper.proceed { mService.changePassport(body) }
		return updateResult?.let {
			getProfileInfo(mapper)
		}
	}

	override suspend fun changePassword(model : ReqModelChangePassword) : Boolean? {
		return networkHelper.proceed { mService.changePassword(model) }
	}
}
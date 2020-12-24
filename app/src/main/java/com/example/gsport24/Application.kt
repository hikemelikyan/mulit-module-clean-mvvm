package com.example.gsport24

import android.app.Application
import com.example.gsport24.di.AppComponent
import com.example.gsport24.di.DependencyInjectionStorage
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.root.di.DeviceID
import com.example.gsport24.root.utils.SharedPreferencesHelper
import com.google.firebase.FirebaseApp
import com.google.gson.Gson
import javax.inject.Inject

class Application : Application() {

	companion object {

		private lateinit var mInstance : com.example.gsport24.Application

		fun getInstance() = mInstance
	}

	lateinit var injectionStorage : DependencyInjectionStorage

	@Inject
	lateinit var mShared : SharedPreferencesHelper

	@Inject
	@DeviceID
	lateinit var deviceId : String

	override fun onCreate() {
		super.onCreate()
		mInstance = this
		FirebaseApp.initializeApp(applicationContext)
		injectionStorage = DependencyInjectionStorage.initialize(this)

		injectionStorage.get<AppComponent>().inject(this)

		mShared.saveDeviceId(deviceId)
	}

	fun updateUserInfo(info : UserDomain) {
		val gson = Gson()
		mShared.saveUserInfo(gson.toJson(info))
	}

	inline fun <reified T> getUserInfo() : T? {
		val `object` = mShared.getUserInfo()
		return `object`?.let {
			val gson = Gson()
			gson.fromJson(`object`, T::class.java)
		}
	}

}
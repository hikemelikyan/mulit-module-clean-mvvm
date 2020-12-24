package com.example.gsport24.firebase

import com.example.gsport24.Application
import com.example.gsport24.domain.entities.CloudNotificationDomain
import com.example.gsport24.firebase.di.FCMServiceComponent
import com.example.gsport24.root.utils.SharedPreferencesHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import javax.inject.Inject

class FCMNotificationsService : FirebaseMessagingService() {

	@Inject
	lateinit var mShared : SharedPreferencesHelper

	private val gson = Gson()

	override fun onCreate() {
		super.onCreate()
		Application.getInstance().injectionStorage.get<FCMServiceComponent>().inject(this)
	}

	override fun onMessageReceived(p0 : RemoteMessage) {
		super.onMessageReceived(p0)
		Notifier.show(this, gson.fromJson(gson.toJson(p0.notification), CloudNotificationDomain::class.java).apply {
			type = p0.data["Type"]?.toIntOrNull()
		})
	}

	override fun onNewToken(p0 : String) {
		mShared.saveFirebaseToken(p0)
	}
}
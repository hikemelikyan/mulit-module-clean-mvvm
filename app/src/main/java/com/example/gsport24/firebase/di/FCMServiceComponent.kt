package com.example.gsport24.firebase.di

import com.example.gsport24.firebase.FCMNotificationsService
import dagger.Subcomponent

@Subcomponent
interface FCMServiceComponent {

	@Subcomponent.Builder
	interface Builder {

		fun build() : FCMServiceComponent
	}

	fun inject(target : FCMNotificationsService)
}
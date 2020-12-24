package com.example.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.di

import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification.PhoneVerificationViewModel
import dagger.Subcomponent

@Subcomponent(modules = [PhoneVerificationBinds::class])
interface PhoneVerificationComponent {

	@Subcomponent.Builder
	interface Builder {

		fun withNetModule(module : NetworkModule) : Builder
		fun withRootModule(module : RootModule) : Builder
		fun build() : PhoneVerificationComponent
	}

	fun inject(target : PhoneVerificationViewModel)

}

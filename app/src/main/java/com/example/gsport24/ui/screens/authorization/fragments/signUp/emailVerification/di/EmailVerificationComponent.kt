package com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.di

import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.EmailVerificationViewModel
import dagger.Subcomponent

@Subcomponent(modules = [EmailVerificationBinds::class])
interface EmailVerificationComponent {

	@Subcomponent.Builder
	interface Builder{
		fun withNetModule(module : NetworkModule) : Builder
		fun withRootModule(module : RootModule) : Builder
		fun build(): EmailVerificationComponent
	}

	fun inject(target : EmailVerificationViewModel)
}
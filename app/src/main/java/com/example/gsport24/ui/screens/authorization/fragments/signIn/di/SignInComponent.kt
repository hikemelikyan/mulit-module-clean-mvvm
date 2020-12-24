package com.example.gsport24.ui.screens.authorization.fragments.signIn.di

import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.ui.screens.authorization.fragments.signIn.SignInViewModel
import dagger.Subcomponent

@Subcomponent(modules = [SignInBinds::class])
interface SignInComponent {

	@Subcomponent.Builder
	interface Builder {

		fun withNetModule(module : NetworkModule) : Builder
		fun withRootModule(module : RootModule) : Builder
		fun build() : SignInComponent
	}

	fun inject(target : SignInViewModel)
}
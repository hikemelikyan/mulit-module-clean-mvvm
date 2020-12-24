package com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword.di

import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword.CreatePasswordViewModel
import dagger.Subcomponent

@Subcomponent(modules = [CreatePasswordComponentBinds::class])
interface CreatePasswordComponent {

	@Subcomponent.Builder
	interface Builder {

		fun withNetModule(module : NetworkModule) : Builder
		fun withRootModule(module : RootModule) : Builder
		fun build() : CreatePasswordComponent
	}

	fun inject(target : CreatePasswordViewModel)

}
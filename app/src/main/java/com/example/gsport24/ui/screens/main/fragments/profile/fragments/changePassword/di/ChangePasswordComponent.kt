package com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword.di

import com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword.ChangePasswordViewModel
import dagger.Subcomponent

@Subcomponent(modules = [ChangePasswordComponentBinds::class])
interface ChangePasswordComponent {

	@Subcomponent.Builder
	interface Builder{
		fun build():ChangePasswordComponent
	}

	fun inject(target : ChangePasswordViewModel)
}
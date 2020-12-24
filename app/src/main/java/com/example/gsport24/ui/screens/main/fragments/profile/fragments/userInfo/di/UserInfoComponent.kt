package com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo.di

import com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword.di.ChangePasswordComponent
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.di.PassportInfoComponent
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo.UserInfoViewModel
import dagger.Subcomponent

@Subcomponent(modules = [UserInfoComponentBinds::class])
interface UserInfoComponent {

	@Subcomponent.Builder
	interface Builder{
		fun build(): UserInfoComponent
	}

	val passportInfoComponent: PassportInfoComponent.Builder
	val changePasswordComponent : ChangePasswordComponent.Builder

	fun inject(target: UserInfoViewModel)
}
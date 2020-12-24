package com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.di

import com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.PassportInfoViewModel
import dagger.Subcomponent

@Subcomponent(modules = [PassportInfoComponentBinds::class])
interface PassportInfoComponent {

	@Subcomponent.Builder
	interface Builder {

		fun build() : PassportInfoComponent
	}

	fun inject(target : PassportInfoViewModel)
}
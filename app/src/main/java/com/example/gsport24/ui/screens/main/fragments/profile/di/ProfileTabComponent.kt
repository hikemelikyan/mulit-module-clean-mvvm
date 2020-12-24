package com.example.gsport24.ui.screens.main.fragments.profile.di

import com.example.gsport24.ui.screens.main.fragments.profile.ProfileViewModel
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.di.BalanceComponent
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo.di.UserInfoComponent
import dagger.Subcomponent

@Subcomponent
interface ProfileTabComponent {

	@Subcomponent.Builder
	interface Builder {

		fun build() : ProfileTabComponent
	}

	val userInfoComponent : UserInfoComponent.Builder
	val userBalanceComponent : BalanceComponent.Builder

	fun inject(target : ProfileViewModel)

}
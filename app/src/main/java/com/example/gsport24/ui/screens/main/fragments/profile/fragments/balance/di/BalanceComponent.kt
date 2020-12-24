package com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.di

import com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.BalanceViewModel
import dagger.Subcomponent

@Subcomponent(modules = [BalanceComponentBinds::class])
interface BalanceComponent {

	@Subcomponent.Builder
	interface Builder{
		fun build() : BalanceComponent
	}

	fun inject(target:BalanceViewModel)
}
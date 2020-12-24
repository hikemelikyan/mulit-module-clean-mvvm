package com.example.gsport24.ui.screens.main.fragments.home.di

import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.ui.screens.main.fragments.home.HomeViewModel
import dagger.Subcomponent

@Subcomponent(modules = [HomeFragmentBinds::class])
interface HomeFragmentComponent {

	@Subcomponent.Builder
	interface Builder {

		fun withNetModule(module : NetworkModule) : Builder
		fun withRootModule(module : RootModule) : Builder
		fun build() : HomeFragmentComponent
	}

	fun inject(target : HomeViewModel)
}
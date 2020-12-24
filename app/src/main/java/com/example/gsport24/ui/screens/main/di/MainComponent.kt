package com.example.gsport24.ui.screens.main.di

import com.example.gsport24.ui.screens.main.MainViewModel
import com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.di.CategoryPageComponent
import com.example.gsport24.ui.screens.main.fragments.home.di.HomeFragmentComponent
import com.example.gsport24.ui.screens.main.fragments.menu.di.NewsFragmentComponent
import com.example.gsport24.ui.screens.main.fragments.notifications.di.NotificationsComponent
import com.example.gsport24.ui.screens.main.fragments.profile.di.ProfileTabComponent
import dagger.Subcomponent

@Subcomponent(modules = [MainComponentBinds::class])
interface MainComponent {

	@Subcomponent.Builder
	interface Builder {
		fun build(): MainComponent
	}
	val categoryPageComponent : CategoryPageComponent.Builder
	val homeFragmentComponent : HomeFragmentComponent.Builder
	val newsFragmentComponent : NewsFragmentComponent.Builder
	val profileTabComponent: ProfileTabComponent.Builder
	val notificationsComponent: NotificationsComponent.Builder

	fun inject(target:MainViewModel)

}
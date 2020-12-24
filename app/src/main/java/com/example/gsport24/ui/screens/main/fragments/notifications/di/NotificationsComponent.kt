package com.example.gsport24.ui.screens.main.fragments.notifications.di

import com.example.gsport24.ui.screens.main.fragments.notifications.NotificationsViewModel
import dagger.Subcomponent

@Subcomponent(modules = [NotificationsComponentBinds::class])
interface NotificationsComponent {

	@Subcomponent.Builder
	interface Builder{
		fun build():NotificationsComponent
	}

	fun inject(target : NotificationsViewModel)
}
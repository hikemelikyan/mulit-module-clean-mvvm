package com.example.gsport24.ui.screens.main.fragments.menu.di

import com.example.gsport24.ui.screens.main.fragments.menu.news.NewsViewModel
import dagger.Subcomponent

@Subcomponent(modules = [NewsFragmentBinds::class])
interface NewsFragmentComponent {

	@Subcomponent.Builder
	interface Builder {

		fun build() : NewsFragmentComponent
	}

	fun inject(target : NewsViewModel)
}
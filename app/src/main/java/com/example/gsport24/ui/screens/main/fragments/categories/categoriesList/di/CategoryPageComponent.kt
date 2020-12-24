package com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.di

import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.CategoryListViewModel
import dagger.Subcomponent

@Subcomponent(modules = [CategoryListBinds::class])
interface CategoryPageComponent {

	@Subcomponent.Builder
	interface Builder {

		fun withNetModule(module : NetworkModule) : Builder
		fun withRootModule(module : RootModule) : Builder
		fun build() : CategoryPageComponent
	}

	fun inject(target : CategoryListViewModel)

}
package com.example.gsport24.ui.screens.main.fragments.menu.di

import com.example.gsport24.domain.useCase.menu.GetMenuUseCase
import com.example.gsport24.domain.useCase.menu.GetMenuUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface NewsFragmentBinds {

	@Binds
	fun bindsGetMenuUseCase(menuUseCaseImpl : GetMenuUseCaseImpl) : GetMenuUseCase

}
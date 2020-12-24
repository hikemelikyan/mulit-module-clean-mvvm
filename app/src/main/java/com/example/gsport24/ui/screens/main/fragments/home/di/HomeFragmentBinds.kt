package com.example.gsport24.ui.screens.main.fragments.home.di

import com.example.gsport24.domain.di.DomainModuleRepositoryBinds
import com.example.gsport24.domain.useCase.home.GetHomeInfoUseCase
import com.example.gsport24.domain.useCase.home.GetHomeInfoUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface HomeFragmentBinds {

	@Binds
	fun bindsHomeInfoUseCase(homeInfoUseCaseImpl : GetHomeInfoUseCaseImpl) : GetHomeInfoUseCase

}
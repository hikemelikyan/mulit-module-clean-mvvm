package com.example.gsport24.ui.screens.main.di

import com.example.gsport24.domain.useCase.profile.get.GetUserPersonalInformationUseCase
import com.example.gsport24.domain.useCase.profile.get.GetUserPersonalInformationUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface MainComponentBinds {

	@Binds
	fun bindsUserInfoUseCase(getUserPersonalInformationUseCaseImpl : GetUserPersonalInformationUseCaseImpl) : GetUserPersonalInformationUseCase

}
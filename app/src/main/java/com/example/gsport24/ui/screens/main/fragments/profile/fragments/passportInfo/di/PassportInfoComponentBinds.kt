package com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.di

import com.example.gsport24.domain.useCase.passportInfo.UpdatePassportInfoUseCase
import com.example.gsport24.domain.useCase.passportInfo.UpdatePassportInfoUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface PassportInfoComponentBinds {

	@Binds
	fun bindsUpdatePassportInfoUseCase(updatePassportInfoUseCaseImpl : UpdatePassportInfoUseCaseImpl) : UpdatePassportInfoUseCase

}
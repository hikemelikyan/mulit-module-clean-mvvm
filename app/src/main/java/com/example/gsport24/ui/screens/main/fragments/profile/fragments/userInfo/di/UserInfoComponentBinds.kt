package com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo.di

import com.example.gsport24.domain.useCase.profile.update.UpdateUserInfoUseCase
import com.example.gsport24.domain.useCase.profile.update.UpdateUserInfoUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UserInfoComponentBinds {

	@Binds
	fun bindsUpdateUserInfoUseCase(updateUserInfoUseCase : UpdateUserInfoUseCaseImpl) : UpdateUserInfoUseCase
}
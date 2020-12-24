package com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword.di

import com.example.gsport24.domain.useCase.profile.changePassword.ChangePasswordUseCase
import com.example.gsport24.domain.useCase.profile.changePassword.ChangePasswordUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ChangePasswordComponentBinds {

	@Binds
	fun bindsChangePasswordUseCase(changePasswordUseCaseImpl : ChangePasswordUseCaseImpl) : ChangePasswordUseCase

}
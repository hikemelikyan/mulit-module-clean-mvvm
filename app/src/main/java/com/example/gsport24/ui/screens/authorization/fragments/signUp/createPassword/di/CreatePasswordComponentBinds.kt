package com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword.di

import com.example.gsport24.domain.di.DomainModuleRepositoryBinds
import com.example.gsport24.domain.useCase.userRegistration.UserRegistrationUseCase
import com.example.gsport24.domain.useCase.userRegistration.UserRegistrationUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface CreatePasswordComponentBinds {

	@Binds
	fun bindsPhoneVerificationUseCase(userRegistrationUseCaseImpl : UserRegistrationUseCaseImpl) : UserRegistrationUseCase

}
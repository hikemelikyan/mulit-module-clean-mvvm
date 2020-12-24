package com.example.gsport24.ui.screens.authorization.fragments.signIn.di

import com.example.gsport24.domain.di.DomainModuleRepositoryBinds
import com.example.gsport24.domain.useCase.login.LogInUseCase
import com.example.gsport24.domain.useCase.login.LogInUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface SignInBinds {

	@Binds
	fun bindsPhoneVerificationUseCase(logInUseCaseImpl : LogInUseCaseImpl) : LogInUseCase
}

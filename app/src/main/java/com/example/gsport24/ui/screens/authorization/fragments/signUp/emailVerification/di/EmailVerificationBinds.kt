package com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification.di

import com.example.gsport24.domain.di.DomainModuleRepositoryBinds
import com.example.gsport24.domain.useCase.emailVerification.EmailVerificationUseCase
import com.example.gsport24.domain.useCase.emailVerification.EmailVerificationUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface EmailVerificationBinds {

	@Binds
	fun bindsEmailVerificationUseCase(emailVerificationUseCase : EmailVerificationUseCaseImpl): EmailVerificationUseCase
}
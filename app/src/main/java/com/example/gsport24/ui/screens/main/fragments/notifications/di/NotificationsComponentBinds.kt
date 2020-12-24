package com.example.gsport24.ui.screens.main.fragments.notifications.di

import com.example.gsport24.domain.useCase.notifications.GetNotificationsListUseCase
import com.example.gsport24.domain.useCase.notifications.GetNotificationsListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface NotificationsComponentBinds {

	@Binds
	fun bindsGetNotificationsListUseCase(getNotificationsListUseCaseImpl : GetNotificationsListUseCaseImpl) : GetNotificationsListUseCase

}
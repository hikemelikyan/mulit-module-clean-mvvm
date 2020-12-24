package com.example.gsport24.ui.screens.main.fragments.profile.fragments.balance.di

import com.example.gsport24.domain.useCase.transactionsHistory.GetTransactionsHistoryAndPaymentMethodsUseCase
import com.example.gsport24.domain.useCase.transactionsHistory.GetTransactionsHistoryAndPaymentMethodsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface BalanceComponentBinds {

	@Binds
	fun bindsBalanceInfoUseCase(getTransactionsHistoryAndPaymentMethodsUseCase : GetTransactionsHistoryAndPaymentMethodsUseCaseImpl) : GetTransactionsHistoryAndPaymentMethodsUseCase

}
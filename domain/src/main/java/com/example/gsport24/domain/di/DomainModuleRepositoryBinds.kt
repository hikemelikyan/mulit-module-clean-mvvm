package com.example.gsport24.domain.di

import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.data.di.NetworkModuleBinds
import com.example.gsport24.data.repository.auth.AuthRepository
import com.example.gsport24.data.repository.auth.AuthRepositoryImpl
import com.example.gsport24.data.repository.category.CategoryRepository
import com.example.gsport24.data.repository.category.CategoryRepositoryImpl
import com.example.gsport24.data.repository.news.NewsRepository
import com.example.gsport24.data.repository.news.NewsRepositoryImpl
import com.example.gsport24.data.repository.notifications.NotificationsRepository
import com.example.gsport24.data.repository.notifications.NotificationsRepositoryImpl
import com.example.gsport24.data.repository.payment.PaymentRepository
import com.example.gsport24.data.repository.payment.PaymentRepositoryImpl
import com.example.gsport24.data.repository.transaction.TransactionsRepository
import com.example.gsport24.data.repository.transaction.TransactionsRepositoryImpl
import com.example.gsport24.data.repository.user.UserRepository
import com.example.gsport24.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, NetworkModuleBinds::class])
abstract class DomainModuleRepositoryBinds {

	@Binds
	abstract fun bindsAuthRepository(authRepositoryImpl : AuthRepositoryImpl) : AuthRepository

	@Binds
	abstract fun bindsCategoryRepository(categoryRepository : CategoryRepositoryImpl) : CategoryRepository

	@Binds
	abstract fun bindsNewsRepository(newsRepositoryImpl : NewsRepositoryImpl) : NewsRepository

	@Binds
	abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository

	@Binds
	abstract fun bindsBalanceRepository(paymentRepositoryImpl : PaymentRepositoryImpl) : PaymentRepository

	@Binds
	abstract fun bindsTransactionsRepository(transactionsRepositoryImpl : TransactionsRepositoryImpl) : TransactionsRepository

	@Binds
	abstract fun bindsNotificationsRepository(notificationsRepositoryImpl : NotificationsRepositoryImpl) : NotificationsRepository
}
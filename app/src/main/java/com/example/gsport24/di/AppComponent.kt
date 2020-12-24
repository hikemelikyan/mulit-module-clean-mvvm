package com.example.gsport24.di

import com.example.gsport24.Application
import com.example.gsport24.data.di.NetworkModule
import com.example.gsport24.data.di.NetworkModuleBinds
import com.example.gsport24.domain.di.DomainModuleRepositoryBinds
import com.example.gsport24.firebase.di.FCMServiceComponent
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.root.di.RootModuleBinds
import com.example.gsport24.ui.screens.authorization.di.AuthComponent
import com.example.gsport24.ui.screens.main.di.MainComponent
import dagger.Component

@Component(
    modules = [
        RootModule::class,
        RootModuleBinds::class,
        NetworkModule::class,
        NetworkModuleBinds::class,
        DomainModuleRepositoryBinds::class,
    ]
)
interface AppComponent {

	@Component.Builder
	interface Builder {

		fun withNetworkModule(networkModule : NetworkModule) : Builder
		fun withRootModule(rootModule : RootModule) : Builder
		fun build() : AppComponent
	}

	val authComponent : AuthComponent.Builder
	val firebaseMessagingServiceComponent : FCMServiceComponent.Builder
	val mainComponent : MainComponent.Builder

	fun inject(target : Application)
}
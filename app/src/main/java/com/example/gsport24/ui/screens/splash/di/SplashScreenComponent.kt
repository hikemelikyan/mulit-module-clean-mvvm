package com.example.gsport24.ui.screens.splash.di

import com.example.gsport24.root.di.RootModuleBinds
import com.example.gsport24.ui.screens.splash.SplashScreenSecondActivity
import dagger.Component

@Component(modules = [RootModuleBinds::class])
interface SplashScreenComponent {
	fun inject(target: SplashScreenSecondActivity)
}
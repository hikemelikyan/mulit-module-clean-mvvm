package com.example.gsport24.root.di

import com.example.gsport24.root.utils.NetworkConnectivityChecker
import com.example.gsport24.root.utils.NetworkConnectivityCheckerImpl
import com.example.gsport24.root.utils.SharedPreferencesHelper
import com.example.gsport24.root.utils.SharedPreferencesHelperImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RootModule::class])
abstract class RootModuleBinds {

    @Binds
    abstract fun provideSharedPreferences(sharedPreferencesHelperImpl: SharedPreferencesHelperImpl): SharedPreferencesHelper

    @Binds
    abstract fun bindsNetworkConnectivityChecker(checkerImpl: NetworkConnectivityCheckerImpl): NetworkConnectivityChecker

}
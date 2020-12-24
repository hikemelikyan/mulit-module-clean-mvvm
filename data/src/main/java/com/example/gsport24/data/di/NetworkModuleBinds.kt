package com.example.gsport24.data.di

import com.example.gsport24.data.root.NetworkHelper
import com.example.gsport24.data.root.NetworkHelperImpl
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.data.root.ResultFactoryImpl
import com.example.gsport24.root.di.RootModule
import com.example.gsport24.root.di.RootModuleBinds
import dagger.Binds
import dagger.Module

@Module(includes = [RootModule::class,RootModuleBinds::class])
interface NetworkModuleBinds {

    @Binds
    fun providesNetworkHelper(helperImpl: NetworkHelperImpl): NetworkHelper

    @Binds
    fun providesResultFactory(resultFactoryImpl: ResultFactoryImpl):ResultFactory

}

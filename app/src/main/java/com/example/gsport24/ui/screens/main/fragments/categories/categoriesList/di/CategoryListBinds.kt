package com.example.gsport24.ui.screens.main.fragments.categories.categoriesList.di

import com.example.gsport24.domain.di.DomainModuleRepositoryBinds
import com.example.gsport24.domain.useCase.category.GetAllCategoriesUseCase
import com.example.gsport24.domain.useCase.category.GetAllCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModuleRepositoryBinds::class])
interface CategoryListBinds {

	@Binds
	fun bindsGetAllCategoriesUseCase(getAllCategoriesUseCase : GetAllCategoriesUseCaseImpl) : GetAllCategoriesUseCase
}

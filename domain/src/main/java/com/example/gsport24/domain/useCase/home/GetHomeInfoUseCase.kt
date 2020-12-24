package com.example.gsport24.domain.useCase.home

import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.domain.entities.NewsDomain
import kotlinx.coroutines.flow.Flow

interface GetHomeInfoUseCase {

	suspend fun getNewsList() : Flow<Result<List<NewsDomain>>>

	suspend fun getCategoriesList() : Flow<Result<List<CategoryDomain>>>

	suspend fun getBannersList() : Flow<Result<List<String>>>
}
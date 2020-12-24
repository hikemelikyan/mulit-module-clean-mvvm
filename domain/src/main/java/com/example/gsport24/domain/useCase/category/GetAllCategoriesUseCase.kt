package com.example.gsport24.domain.useCase.category

import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.CategoryDomain
import kotlinx.coroutines.flow.Flow

interface GetAllCategoriesUseCase {

	suspend fun getAll() : Flow<Result<List<CategoryDomain>>>

}
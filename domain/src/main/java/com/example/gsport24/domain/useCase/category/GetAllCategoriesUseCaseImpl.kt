package com.example.gsport24.domain.useCase.category

import com.example.gsport24.data.repository.category.CategoryRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.domain.mappers.CategoryResponseToDomainMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCaseImpl
@Inject
constructor(
	private val categoryRepository : CategoryRepository,
	private val resultFactory : ResultFactory
) : GetAllCategoriesUseCase {

	override suspend fun getAll() : Flow<Result<List<CategoryDomain>>> {
		return resultFactory.getListResult { categoryRepository.getAll(CategoryResponseToDomainMapper) }
	}

}
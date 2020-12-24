package com.example.gsport24.data.repository.category

import com.example.gsport24.root.mapperBase.Mapper
import com.example.gsport24.data.model.responseModels.CategoryResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.data.root.NetworkHelper
import com.example.gsport24.data.services.ICategoryService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CategoryRepositoryImpl
@Inject
constructor(
	private val networkHelper : NetworkHelper,
	retrofit : Retrofit
) : CategoryRepository {

	private val mService : ICategoryService = retrofit.create()

	override suspend fun <T : Entity> getAll(mapper : Mapper<CategoryResponseModel, T>) : List<T>? {
		return networkHelper.proceed { mService.getAll() }?.map {
			mapper(it)
		}
	}
}
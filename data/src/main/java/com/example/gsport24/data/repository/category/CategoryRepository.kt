package com.example.gsport24.data.repository.category

import com.example.gsport24.root.mapperBase.Mapper
import com.example.gsport24.data.model.responseModels.CategoryResponseModel
import com.example.gsport24.data.root.Entity

interface CategoryRepository {

	suspend fun <T : Entity> getAll(mapper : Mapper<CategoryResponseModel, T>) : List<T>?

}
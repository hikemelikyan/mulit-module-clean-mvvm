package com.example.gsport24.data.services

import com.example.gsport24.data.model.responseModels.CategoryResponseModel
import com.example.gsport24.data.model.responseModels.root.Response
import retrofit2.http.GET

internal interface ICategoryService {
	@GET("/api/Category/GetAll")
	suspend fun getAll():Response<List<CategoryResponseModel>>
}
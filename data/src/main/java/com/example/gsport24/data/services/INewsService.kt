package com.example.gsport24.data.services

import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.NewsDetailsResponseModel
import com.example.gsport24.data.model.responseModels.NewsResponseModel
import com.example.gsport24.data.model.responseModels.root.PaginationResponseModel
import com.example.gsport24.data.model.responseModels.root.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface INewsService {

	@POST("/api/News/GetList")
	suspend fun getList(@Body paginationRequestModel : PaginationRequestModel) : Response<PaginationResponseModel<NewsResponseModel>>

	@GET("/api/News/Details/{id}")
	suspend fun getDetails(@Path("id") id : Int) : Response<NewsDetailsResponseModel>

}
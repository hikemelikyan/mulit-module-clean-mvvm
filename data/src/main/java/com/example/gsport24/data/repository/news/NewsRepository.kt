package com.example.gsport24.data.repository.news

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.NewsDetailsResponseModel
import com.example.gsport24.data.model.responseModels.NewsResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.mapperBase.Mapper
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

	suspend fun <T : Entity> getList(model : PaginationRequestModel, mapper : Mapper<NewsResponseModel, T>) : Flow<PagingData<T>>

	suspend fun <T : Entity> getHomeList(model : PaginationRequestModel, mapper : Mapper<NewsResponseModel, T>) : List<T>?

	suspend fun getById(id : Int) : NewsDetailsResponseModel?

}
package com.example.gsport24.data.repository.news

import androidx.paging.PagingData
import androidx.paging.map
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.NewsDetailsResponseModel
import com.example.gsport24.data.model.responseModels.NewsResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.data.root.NetworkHelper
import com.example.gsport24.data.services.INewsService
import com.example.gsport24.root.mapperBase.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class NewsRepositoryImpl
@Inject
constructor(
	private val networkHelper : NetworkHelper,
	retrofit : Retrofit
) : NewsRepository {

	private val mService : INewsService = retrofit.create()

	override suspend fun <T : Entity> getList(model : PaginationRequestModel, mapper : Mapper<NewsResponseModel, T>) : Flow<PagingData<T>> {
		val result : Flow<PagingData<NewsResponseModel>> = networkHelper.withModel(model) {
			paginate { mService.getList(model) }
		}
		return result.map {
			it.map {
				mapper(it)
			}
		}
	}

	override suspend fun <T : Entity> getHomeList(model : PaginationRequestModel, mapper : Mapper<NewsResponseModel, T>) : List<T>? {
		val result = networkHelper.proceed { mService.getList(model) }
		return result?.data?.map { mapper(it) }
	}

	override suspend fun  getById(id : Int) : NewsDetailsResponseModel? {
		return networkHelper.proceed { mService.getDetails(id) }
	}

}
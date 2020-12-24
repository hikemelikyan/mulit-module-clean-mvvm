package com.example.gsport24.data.repository.notifications

import androidx.paging.PagingData
import androidx.paging.map
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.NotificationResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.data.root.NetworkHelper
import com.example.gsport24.data.services.INotificationsService
import com.example.gsport24.root.mapperBase.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class NotificationsRepositoryImpl
@Inject
constructor(
	private val networkHelper : NetworkHelper,
	retrofit : Retrofit
) : NotificationsRepository {

	private val mService : INotificationsService = retrofit.create()

	override suspend fun <T : Entity> getNotificationsRepository(model : PaginationRequestModel, mapper : Mapper<NotificationResponseModel, T>) : Flow<PagingData<T>> {
		val result : Flow<PagingData<NotificationResponseModel>> = networkHelper.withModel(model) {
			paginate { mService.getNotificationsList(model) }
		}
		return result.map { it.map { mapper(it) } }
	}

}
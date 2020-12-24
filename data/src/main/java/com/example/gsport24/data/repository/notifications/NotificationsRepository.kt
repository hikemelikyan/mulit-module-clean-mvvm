package com.example.gsport24.data.repository.notifications

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.NotificationResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.mapperBase.Mapper
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {

	suspend fun <T : Entity> getNotificationsRepository(model : PaginationRequestModel, mapper : Mapper<NotificationResponseModel, T>) : Flow<PagingData<T>>?

}
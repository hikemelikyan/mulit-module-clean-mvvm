package com.example.gsport24.domain.useCase.notifications

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.NotificationDomain
import kotlinx.coroutines.flow.Flow

interface GetNotificationsListUseCase {

	suspend fun getNotificationsList(model:PaginationRequestModel) : Flow<Result<PagingData<NotificationDomain>>>

}
package com.example.gsport24.domain.useCase.notifications

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.repository.notifications.NotificationsRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.NotificationDomain
import com.example.gsport24.domain.mappers.NotificationDomainMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationsListUseCaseImpl
@Inject
constructor(
	private val notificationsRepository : NotificationsRepository,
	private val resultFactory : ResultFactory
) : GetNotificationsListUseCase {

	override suspend fun getNotificationsList(model : PaginationRequestModel) : Flow<Result<PagingData<NotificationDomain>>> {
		return resultFactory.getPagingResult { notificationsRepository.getNotificationsRepository(model, NotificationDomainMapper) }
	}

}
package com.example.gsport24.ui.screens.main.fragments.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.gsport24.Application
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.domain.entities.NotificationDomain
import com.example.gsport24.domain.useCase.notifications.GetNotificationsListUseCase
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.ui.screens.main.fragments.notifications.di.NotificationsComponent
import javax.inject.Inject

class NotificationsViewModel : BaseViewModel() {

	@Inject
	lateinit var useCase : GetNotificationsListUseCase

	private val _notifications : MutableLiveData<PagingData<NotificationDomain>> by lazy { MutableLiveData() }
	val notifications : LiveData<PagingData<NotificationDomain>>
		get() = _notifications

	init {
		inject()
		getNotificationsList()
	}

	private fun getNotificationsList() {
		launchDefault {
			val result = useCase.getNotificationsList(PaginationRequestModel())
			result doOnSuccess {
				_notifications.postValue(it)
			}
		}
	}

	override fun inject() {
		Application.getInstance().injectionStorage.get<NotificationsComponent>().inject(this)
	}

	override fun releaseInjection() {
		Application.getInstance().injectionStorage.release<NotificationsComponent>()
	}
}
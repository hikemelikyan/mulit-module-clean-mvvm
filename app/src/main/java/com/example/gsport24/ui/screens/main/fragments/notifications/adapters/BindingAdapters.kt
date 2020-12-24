package com.example.gsport24.ui.screens.main.fragments.notifications.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.domain.entities.NotificationDomain
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object BindingAdapters {

	@JvmStatic
	@BindingAdapter("notifications_list")
	fun setNotificationsList(rv:RecyclerView, valueToBind: LiveData<PagingData<NotificationDomain>>){
		val adapter = rv.adapter as NotificationsAdapter
		valueToBind.observeForever {
			GlobalScope.launch {
				adapter.submitData(it)
			}
		}
	}

}
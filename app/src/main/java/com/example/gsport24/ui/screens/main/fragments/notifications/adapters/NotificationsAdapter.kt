package com.example.gsport24.ui.screens.main.fragments.notifications.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.AdapterNotificationListItemBinding
import com.example.gsport24.domain.entities.NotificationDomain
import com.example.gsport24.mvvm.ui.BasePagingAdapter
import com.example.gsport24.mvvm.ui.BaseViewHolder
import com.example.gsport24.root.ext.convertServerDateTo
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.root.utils.getDiffCallback

class NotificationsAdapter : BasePagingAdapter<NotificationDomain,AdapterNotificationListItemBinding>(getDiffCallback()) {

	override fun inflate(inflater : LayoutInflater, parent : ViewGroup?, attachToParent : Boolean) : AdapterNotificationListItemBinding {
		return AdapterNotificationListItemBinding.inflate(inflater,parent,attachToParent)
	}

	override fun BaseViewHolder<AdapterNotificationListItemBinding, NotificationDomain>.bindActionTo(data : NotificationDomain) {
		binding.apply {
			tvNotificationDate.text = data.date.convertServerDateTo { DatePatterns.DAY_MONTH_YEAR }
			tvNotificationDescription.text = data.description
		}
	}
}
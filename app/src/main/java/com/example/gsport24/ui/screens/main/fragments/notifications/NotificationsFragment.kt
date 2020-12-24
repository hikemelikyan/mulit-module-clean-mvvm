package com.example.gsport24.ui.screens.main.fragments.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.FragmentNotificationsBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.ui.screens.main.fragments.notifications.adapters.NotificationsAdapter

class NotificationsFragment: BaseRequestFragment<FragmentNotificationsBinding, NotificationsViewModel>() {

	override val hasMainRequest : Boolean
		get() = true
	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentNotificationsBinding
		get() = FragmentNotificationsBinding::inflate

	override val viewModelType : Class<NotificationsViewModel>
		get() = NotificationsViewModel::class.java

	override fun initView(binding : FragmentNotificationsBinding, viewModel : NotificationsViewModel) {
		binding.apply {
			setViewModel(viewModel)
			rvNotifications.adapter = NotificationsAdapter()
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {

	}
}
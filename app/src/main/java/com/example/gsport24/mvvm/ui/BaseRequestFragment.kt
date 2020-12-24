package com.example.gsport24.mvvm.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.gsport24.R
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.launch
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.splash.SplashScreenFirstActivity

abstract class BaseRequestFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {

	open val hasMainRequest = false

	open val hasEmptyState : Pair<Boolean, StateLayout.EmptyModel?> = Pair(false, null)

	private val _viewModel by lazy { ViewModelProvider.NewInstanceFactory().create(viewModelType) }
	val mViewModel : VM
		get() = _viewModel

	protected abstract val viewModelType : Class<VM>

	@Deprecated("This function is deprecated for BaseRequestFragment child", ReplaceWith("initView(binding:VB,viewModel:VM)"))
	override fun initView(binding : VB) {
	}

	abstract fun initView(binding : VB, viewModel : VM)

	override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
		savedInstanceState?.putBoolean(DISCARD_CONTENT, true)
		super.onCreateView(inflater, container, savedInstanceState)
		initView(mBinding, _viewModel)
		_viewModel.viewCommands.observe(viewLifecycleOwner) {
			proceedInternalCommands(it)
		}
		return if (hasMainRequest) StateLayout.create(requireContext()) {
			withComponentFragment(this@BaseRequestFragment)
			withContent(mBinding.root)
			withStateListener(_viewModel.uiState)
			if (hasEmptyState.first)
				hasEmptyState.second?.let { withEmptyModel(it) }
			attach()
		} else mBinding.root
	}

	private fun proceedInternalCommands(command : ViewCommand) {
		when (command) {
			is Commands.NetworkError -> showNetworkErrorSnackBar(getString(R.string.default_network_error_message))
			is Commands.ShowMessage -> showToast(command.resId)
			is Commands.ShowMessageText -> showToast(command.errorMessage)
			is Commands.ReLaunchApp -> launch(flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) { SplashScreenFirstActivity::class.java }
			else -> proceedViewCommand(command)
		}
	}

	protected abstract fun proceedViewCommand(command : ViewCommand)

}
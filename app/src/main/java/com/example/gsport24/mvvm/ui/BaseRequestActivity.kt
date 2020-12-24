package com.example.gsport24.mvvm.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.gsport24.R
import com.example.gsport24.mvvm.vm.BaseViewModel
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.launch
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.splash.SplashScreenFirstActivity

abstract class BaseRequestActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

	private val _viewModel by lazy { ViewModelProvider.NewInstanceFactory().create(viewModelType) }
	val mViewModel : VM
		get() = _viewModel

	protected abstract val viewModelType : Class<VM>

	protected abstract fun initView(binding : VB, viewModel : VM)

	@Deprecated("This function is deprecated for BaseRequestActivity child", ReplaceWith("initView(binding:VB,viewModel:VM)"))
	override fun initView(binding : VB) {
	}

	override fun onCreate(savedInstanceState : Bundle?) {
		savedInstanceState?.putBoolean(DISCARD_CONTENT, true)
		super.onCreate(savedInstanceState)
		initView(mBinding, _viewModel)
		_viewModel.viewCommands.observe(this) {
			proceedInternalCommands(it)
		}
		setContentView(mBinding.root)
	}

	fun getContent(){

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
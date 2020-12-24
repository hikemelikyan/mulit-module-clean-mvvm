package com.example.gsport24.ui.screens.main.fragments.profile.fragments.changePassword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gsport24.databinding.FragmentChangePasswordBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.shared.LoadingButton
import com.example.gsport24.shared.ext.validationError
import com.example.gsport24.ui.commands.Commands

class ChangePasswordFragment : BaseRequestFragment<FragmentChangePasswordBinding, ChangePasswordViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentChangePasswordBinding
		get() = FragmentChangePasswordBinding::inflate

	override val viewModelType : Class<ChangePasswordViewModel>
		get() = ChangePasswordViewModel::class.java

	override fun initView(binding : FragmentChangePasswordBinding, viewModel : ChangePasswordViewModel) {
		binding.apply {
			lifecycleOwner = this@ChangePasswordFragment
			setViewModel(viewModel)
			tvSave.setOnClickListener {
				if (formIsInValid()) {
					validateForm()
					(it as LoadingButton).isLoading(false)
				} else {
					viewModel.changePassword()
				}
			}
		}
	}

	private fun validateForm() {
		mBinding.apply {
			if (mViewModel.oldPassword.value == null || mViewModel.oldPassword.value!!.length < 6) {
				etOldPassword.validationError { it.length > 6 }
			}
			if (mViewModel.newPassword.value == null || mViewModel.newPassword.value!!.length < 6) {
				etNewPassword.validationError { it.length > 6 }
			}
			if (mViewModel.confirmPassword.value == null || mViewModel.confirmPassword.value!!.length < 6) {
				etConfirmPassword.validationError { it == etNewPassword.text.toString() }
			}
		}
	}

	private fun formIsInValid() : Boolean {
		return mViewModel.oldPassword.value.isNullOrEmpty() ||
				mViewModel.newPassword.value.isNullOrEmpty() ||
				mViewModel.confirmPassword.value.isNullOrEmpty()
	}

	override fun proceedViewCommand(command : ViewCommand) {
		if(command is Commands.PasswordUpdated){
			findNavController().popBackStack()
		}
	}
}
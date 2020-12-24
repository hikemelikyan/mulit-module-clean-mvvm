package com.example.gsport24.ui.screens.authorization.fragments.signIn

import android.content.Intent
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gsport24.databinding.FragmentSignInBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.launch
import com.example.gsport24.shared.ext.validationError
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.main.MainActivity

class SignInFragment : BaseRequestFragment<FragmentSignInBinding, SignInViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignInBinding
		get() = FragmentSignInBinding::inflate

	override val viewModelType : Class<SignInViewModel>
		get() = SignInViewModel::class.java

	override fun initView(binding : FragmentSignInBinding, viewModel : SignInViewModel) {
		sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
		binding.apply {
			setViewModel(viewModel)
			tvSignIn.setOnClickListener {
				if (!mViewModel.userName.value.isNullOrEmpty() && !mViewModel.password.value.isNullOrEmpty()) {
					mViewModel.logIn()
				} else {
					if (mViewModel.userName.value.isNullOrEmpty()) {
						etPhone.validationError { it.isNotEmpty() }
					}
					if (mViewModel.password.value.isNullOrEmpty()) {
						etPassword.validationError { it.isNotEmpty() }
					}
					tvSignIn.isLoading(false)
				}
			}
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {
		when (command) {
			is Commands.NavigateToMain -> navigateToMain()
		}
	}

	private fun navigateToMain() {
		launch(flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) { MainActivity::class.java }
	}

}
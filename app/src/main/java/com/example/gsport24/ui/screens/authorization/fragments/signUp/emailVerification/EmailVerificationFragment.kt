package com.example.gsport24.ui.screens.authorization.fragments.signUp.emailVerification

import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.FrameMetricsAggregator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.example.gsport24.R
import com.example.gsport24.databinding.FragmentEmailVerificationBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.setKeyboardListeners
import com.example.gsport24.root.ext.show
import com.example.gsport24.shared.ext.disable
import com.example.gsport24.shared.ext.validationError
import com.example.gsport24.ui.commands.Commands

class EmailVerificationFragment : BaseRequestFragment<FragmentEmailVerificationBinding, EmailVerificationViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentEmailVerificationBinding
		get() = FragmentEmailVerificationBinding::inflate

	override val viewModelType : Class<EmailVerificationViewModel>
		get() = EmailVerificationViewModel::class.java

	override fun initView(binding : FragmentEmailVerificationBinding, viewModel : EmailVerificationViewModel) {
		sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
		binding.apply {
			setViewModel(viewModel)
			root.setKeyboardListeners(etCode, etEmail)
			tvSendCode.setOnClickListener {
				if (mViewModel.isCodeSent.value != null) {
					if (mViewModel.code.value.isNullOrEmpty()) {
						etCode.validationError { it.length >= 4 }
						tvSendCode.isLoading(false)
					} else {
						mViewModel.verifyEmail()
					}
					mViewModel.verifyEmail()
				} else {
					mViewModel.getSmsCodeByEmail()
				}
			}
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {
		when (command) {
			is Commands.CodeSent -> showCodeEdit()
			is Commands.EmailVerified -> navigateToPersonalInfo(command.codeId)
		}
	}

	private fun showCodeEdit() {
		val set2 = ConstraintSet()
		set2.clone(mBinding.rootLayout.also {
			mBinding.etCode.show()
			mBinding.etEmail.disable()
			mBinding.etEmail.onEditorAction(EditorInfo.IME_ACTION_NEXT)
			mBinding.tvSendCode.setText(getString(R.string.next))
		})

		val transition : Transition = ChangeBounds()
		transition.interpolator = OvershootInterpolator()
		transition.duration = FrameMetricsAggregator.ANIMATION_DURATION.toLong()
		set2.applyTo(mBinding.rootLayout)
		TransitionManager.beginDelayedTransition(mBinding.rootLayout, transition)
	}

	private fun navigateToPersonalInfo(codeId : Int) {
		mBinding.apply {
			val extras = FragmentNavigatorExtras(
				appIcon to appIcon.transitionName,
				title to title.transitionName
			)
			findNavController().navigate(EmailVerificationFragmentDirections.actionEmailVerificationFragmentToPersonalInfoFragment(codeId), extras)
		}
	}
}
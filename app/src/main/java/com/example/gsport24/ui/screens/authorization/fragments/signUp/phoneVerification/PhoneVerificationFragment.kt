package com.example.gsport24.ui.screens.authorization.fragments.signUp.phoneVerification

import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.FrameMetricsAggregator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.example.gsport24.R
import com.example.gsport24.databinding.FragmentPhoneVerificationBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.setKeyboardListeners
import com.example.gsport24.root.ext.show
import com.example.gsport24.shared.ext.disable
import com.example.gsport24.shared.ext.validationError
import com.example.gsport24.ui.commands.Commands

class PhoneVerificationFragment : BaseRequestFragment<FragmentPhoneVerificationBinding, PhoneVerificationViewModel>() {

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentPhoneVerificationBinding
		get() = FragmentPhoneVerificationBinding::inflate

	override val viewModelType : Class<PhoneVerificationViewModel>
		get() = PhoneVerificationViewModel::class.java

	override fun initView(binding : FragmentPhoneVerificationBinding, viewModel : PhoneVerificationViewModel) {
		sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
		binding.apply {
			setViewModel(viewModel)
			root.setKeyboardListeners(etPin, etPhone)
			tvSendCode.setOnClickListener {
				if (mViewModel.codeId.value != null) {
					if (mViewModel.phoneCode.value.isNullOrEmpty()) {
						etPin.validationError { it.length >= 4 }
						tvSendCode.isLoading(false)
					} else {
						mViewModel.verifyPhoneNumber()
					}
				} else {
					if (mViewModel.phoneNumber.value.isNullOrEmpty()) {
						etPhone.validationError { it.length in 9..12 }
						tvSendCode.isLoading(false)
					} else {
						mViewModel.getSmsCode()
					}
				}
			}
		}
	}

	override fun proceedViewCommand(command : ViewCommand) {
		when (command) {
			is Commands.CodeSent -> showCodeEdit()
			is Commands.PhoneVerified -> navigateToEmailVerification(command.codeId)
		}
	}

	private fun showCodeEdit() {
		val set2 = ConstraintSet()
		set2.clone(mBinding.rootLayout.also {
			mBinding.etPin.show()
			mBinding.etPhone.disable()
			mBinding.etPhone.onEditorAction(EditorInfo.IME_ACTION_NEXT)
			mBinding.tvSendCode.setText(getString(R.string.next))
		})

		val transition : Transition = ChangeBounds()
		transition.interpolator = OvershootInterpolator()
		transition.duration = FrameMetricsAggregator.ANIMATION_DURATION.toLong()
		set2.applyTo(mBinding.rootLayout)
		TransitionManager.beginDelayedTransition(mBinding.rootLayout, transition)
	}

	private fun navigateToEmailVerification(codeId : Int) {
		mBinding.apply {
			val extras = FragmentNavigatorExtras(
				appIcon to appIcon.transitionName,
				tvSendCode to tvSendCode.transitionName,
				title to title.transitionName
			)
//			navigateTo(PhoneVerificationFragmentDirections.actionPhoneVerificationFragmentToEmailVerificationFragment(codeId), extras)
		}
	}
}
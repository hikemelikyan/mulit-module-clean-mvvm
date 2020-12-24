package com.example.gsport24.ui.screens.authorization.fragments.signUp.createPassword

import android.content.Intent
import android.graphics.Typeface
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.gsport24.R
import com.example.gsport24.databinding.FragmentCreatePasswordBinding
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.hide
import com.example.gsport24.root.ext.launch
import com.example.gsport24.root.ext.show
import com.example.gsport24.shared.ext.validationError
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.main.MainActivity

class CreatePasswordFragment : BaseRequestFragment<FragmentCreatePasswordBinding, CreatePasswordViewModel>() {

	private val args : CreatePasswordFragmentArgs by navArgs()
	var isChecked = false

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreatePasswordBinding
		get() = FragmentCreatePasswordBinding::inflate

	override val viewModelType : Class<CreatePasswordViewModel>
		get() = CreatePasswordViewModel::class.java

	override fun initView(binding : FragmentCreatePasswordBinding, viewModel : CreatePasswordViewModel) {
		sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
		binding.apply {
			setViewModel(viewModel)
			initCheckbox()
			tvRegister.setOnClickListener {
				if (mViewModel.password.value == null) {
					etPassword.validationError { it.length > 8 }
					etSubmitPassword.validationError { it.length > 8 }
					tvRegister.isLoading(false)
				} else {
					if (mViewModel.password2.value == null || mViewModel.password.value != mViewModel.password2.value) {
						etSubmitPassword.validationError { mViewModel.password.value == mViewModel.password2.value }
						tvRegister.isLoading(false)
					} else {
						if (isChecked) {
							mViewModel.registerUser(args.requestModel.apply {
								password = mViewModel.password.value.toString()
							})
						} else {
							showToast(R.string.please_check_out_our_terms_and_conditions)
							tvRegister.isLoading(false)
						}
					}
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

	private fun FragmentCreatePasswordBinding.initCheckbox() {
		ivCheckedIcon.hide()
		rlCheckbox.setOnClickListener {
			if (isChecked) {
				ivCheckedIcon.hide()
			} else {
				ivCheckedIcon.show()
			}
			isChecked = !isChecked
		}
		initCheckboxText()
	}

	private fun FragmentCreatePasswordBinding.initCheckboxText() {

		fun SpannableString.markText(text : String) : SpannableString {
			this.setSpan(
				StyleSpan(Typeface.BOLD),
				this.indexOf(text),
				this.indexOf(text) + text.length,
				SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
			)
			return this
		}

		fun SpannableString.setClickable(text : String, click : () -> Unit) : SpannableString {
			this.setSpan(
				object : ClickableSpan() {
					override fun onClick(p0 : View) {
						click()
					}

					override fun updateDrawState(ds : TextPaint) {
						ds.isUnderlineText = false
					}
				},
				this.indexOf(text),
				this.indexOf(text) + text.length,
				SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
			)
			return this
		}

		// TODO: 12/2/2020 add to resources
		val spannableString = SpannableString(tvTermsAndPolicy.text)
		spannableString.setClickable("Условия и Положения") {
			showToast("Условия и Положения")
		}
		spannableString.setClickable("Условия Конфидециальности") {
			showToast("Условия Конфидециальности")
		}
		spannableString.markText("Условия и Положения")
		spannableString.markText("Условия Конфидециальности")
		tvTermsAndPolicy.movementMethod = LinkMovementMethod.getInstance()
		tvTermsAndPolicy.text = spannableString
	}
}


package com.example.gsport24.ui.screens.main.fragments.profile.fragments.userInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.Application
import com.example.gsport24.R
import com.example.gsport24.data.model.requestModels.ReqModelChangeUserInfo
import com.example.gsport24.databinding.FragmentUserInfoBinding
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.ui.TitleAdapter
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.convertFrom
import com.example.gsport24.root.ext.convertServerDateTo
import com.example.gsport24.root.ext.convertTo
import com.example.gsport24.root.ext.toServerDate
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.shared.DatePickerDialog
import com.example.gsport24.shared.LoadingButton
import com.example.gsport24.shared.enums.GenderEnum
import com.example.gsport24.shared.ext.validationError
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.authorization.fragments.signUp.personalInfo.EnumRecyclerAdapter
import com.example.gsport24.ui.screens.authorization.fragments.signUp.personalInfo.EnumRecyclerItem
import com.example.gsport24.ui.screens.main.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class UserInfoFragment : BaseRequestFragment<FragmentUserInfoBinding, UserInfoViewModel>() {

	private val userInfo:UserDomain? = Application.getInstance().getUserInfo()

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserInfoBinding
		get() = FragmentUserInfoBinding::inflate

	override val viewModelType : Class<UserInfoViewModel>
		get() = UserInfoViewModel::class.java

	override fun initView(binding : FragmentUserInfoBinding, viewModel : UserInfoViewModel) {
		binding.apply {
			setViewModel(viewModel)
			val mActivity = requireActivity() as MainActivity
			mActivity.setToolbarTitle(getString(R.string.profile))
			userInfo?.let {
				etName.setText(it.firstName)
				etSurname.setText(it.lastName)
				etSecondName.setText(it.secondName)
				etGender.setText(GenderEnum.values()[it.gender].name)
				etDOB.setText(it.dateOfBirth.convertServerDateTo { DatePatterns.DAY_MONTH_YEAR })
				etPhone.setText(it.phone)
				etEmail.setText(it.email)
			}
			tvPassportInfo.setOnClickListener {
				findNavController().navigate(UserInfoFragmentDirections.actionUserInfoFragmentToPassportInfoFragment())
			}
			tvChangePassword.setOnClickListener {
				findNavController().navigate(UserInfoFragmentDirections.actionUserInfoFragmentToChangePasswordFragment())
			}
			etGender.setOnClickListener {
				showGenderPickerBottomFragment {
					etGender.setText(it.name)
				}
			}
			etDOB.setOnClickListener { showDatePicker { etDOB.setText(it.convertTo { DatePatterns.DAY_MONTH_YEAR }) } }
			tvSave.setOnClickListener {
				if (formIsInvalid()) {
					validateForm()
					(it as LoadingButton).isLoading(false)
				} else {
					mViewModel.updateUserInfo(getValidUserInfo())
				}
			}
		}
	}

	private fun FragmentUserInfoBinding.formIsInvalid() : Boolean {
		return etName.text.isNullOrEmpty() ||
				etDOB.text.isNullOrEmpty() ||
				etGender.text.isNullOrEmpty() ||
				etPhone.text.isNullOrEmpty() ||
				etSecondName.text.isNullOrEmpty() ||
				etEmail.text.isNullOrEmpty()
	}

	private fun FragmentUserInfoBinding.validateForm() {
		if (etName.text.isNullOrEmpty()) {
			etName.validationError { it.isNotEmpty() }
		}
		if (etDOB.text.isNullOrEmpty()) {
			etDOB.validationError { it.isNotEmpty() }
		}
		if (etGender.text.isNullOrEmpty()) {
			etGender.validationError { it.isNotEmpty() }
		}
		if (etPhone.text.isNullOrEmpty()) {
			etPhone.validationError { it.isNotEmpty() }
		}
		if (etSecondName.text.isNullOrEmpty()) {
			etSecondName.validationError { it.isNotEmpty() }
		}
		if (etSurname.text.isNullOrEmpty()) {
			etSurname.validationError { it.isNotEmpty() }
		}
		if (etEmail.text.isNullOrEmpty()) {
			etEmail.validationError { it.isNotEmpty() }
		}
	}

	private fun FragmentUserInfoBinding.getValidUserInfo() : ReqModelChangeUserInfo {
		return ReqModelChangeUserInfo(
			etDOB.text.toString().convertFrom { DatePatterns.DAY_MONTH_YEAR }.toServerDate(),
			etEmail.text.toString(),
			etSecondName.text.toString(),
			etName.text.toString(),
			1,
			etSurname.text.toString(),
			etPhone.text.toString(),
		)
	}

	private fun showDatePicker(block : (Date) -> Unit) {
		val current = Calendar.getInstance()
		val dialog = DatePickerDialog(requireContext(), { _, p1, p2, p3 ->
			val cal = Calendar.getInstance()
			cal[Calendar.YEAR] = p1
			cal[Calendar.MONTH] = p2
			cal[Calendar.DAY_OF_MONTH] = p3
			block(cal.time)
		}, current[Calendar.YEAR] - 21, current[Calendar.MONTH], current[Calendar.MONTH])
		dialog.datePicker.maxDate = Calendar.getInstance().apply {
			set(current[Calendar.YEAR] - 21, current[Calendar.MONTH], current[Calendar.MONTH], 0, 0)
		}.timeInMillis
		dialog.show()
	}

	private fun showGenderPickerBottomFragment(onSelectedAction : (EnumRecyclerItem) -> Unit) {
		val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
		bottomSheetDialog.setContentView(RecyclerView(requireContext()).apply {
			layoutManager = LinearLayoutManager(requireContext())
			val itemAdapter = EnumRecyclerAdapter {
				onSelectedAction(it)
				bottomSheetDialog.dismiss()
			}
			adapter = ConcatAdapter(TitleAdapter(R.string.choose_your_gender), itemAdapter)
			itemAdapter.submitList(GenderEnum.values().toMutableList().map {
				EnumRecyclerItem(it.ordinal, it.name, false)
			})
		})
		bottomSheetDialog.show()
	}

	override fun proceedViewCommand(command : ViewCommand) {
		if(command is Commands.UserInfoUpdated){
			findNavController().popBackStack()
		}
	}

}
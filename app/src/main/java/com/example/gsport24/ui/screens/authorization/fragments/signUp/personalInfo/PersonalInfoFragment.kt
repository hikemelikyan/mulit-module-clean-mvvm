package com.example.gsport24.ui.screens.authorization.fragments.signUp.personalInfo

import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gsport24.R
import com.example.gsport24.data.model.requestModels.ReqModelRegister
import com.example.gsport24.databinding.FragmentPersonalInfoBinding
import com.example.gsport24.mvvm.ui.BaseFragment
import com.example.gsport24.mvvm.ui.TitleAdapter
import com.example.gsport24.root.ext.convertFrom
import com.example.gsport24.root.ext.convertTo
import com.example.gsport24.root.ext.setKeyboardListeners
import com.example.gsport24.root.ext.toServerDate
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.shared.DatePickerDialog
import com.example.gsport24.shared.enums.GenderEnum
import com.example.gsport24.shared.ext.validationError
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class PersonalInfoFragment : BaseFragment<FragmentPersonalInfoBinding>() {

	private val args : PersonalInfoFragmentArgs by navArgs()

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalInfoBinding
		get() = FragmentPersonalInfoBinding::inflate

	override fun initView(binding : FragmentPersonalInfoBinding) {
		sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
		binding.apply {
			root.setKeyboardListeners(etName, etSecondName, etSurname)
			etDOB.setOnClickListener { showDatePicker { etDOB.setText(it.convertTo { DatePatterns.DAY_MONTH_YEAR }) } }
			etGender.setOnClickListener {
				showGenderPickerBottomFragment {
					etGender.setText(it.name)
				}
			}
			tvNext.setOnClickListener {
				if (formIsValid()) {
					val extras = FragmentNavigatorExtras(
						appIcon to appIcon.transitionName,
						tvNext to tvNext.transitionName,
						title to title.transitionName
					)
					findNavController().navigate(PersonalInfoFragmentDirections.actionPersonalInfoFragmentToCreatePasswordFragment(getValidRequestModel()), extras)
				} else
					showValidationErrors()
			}
		}
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

	private fun formIsValid() : Boolean {
		return with(mBinding) {
			etName.text.isNotEmpty()
					&& etSecondName.text.isNotEmpty()
					&& etSurname.text.isNotEmpty()
					&& etDOB.text.isNotEmpty()
					&& etGender.text != null
		}
	}

	private fun showValidationErrors() {
		mBinding.apply {
			if (etName.text.isEmpty()) {
				etName.validationError { it.isNotEmpty() }
			}
			if (etSecondName.text.isEmpty()) {
				etSecondName.validationError { it.isNotEmpty() }
			}
			if (etSurname.text.isEmpty()) {
				etSurname.validationError { it.isNotEmpty() }
			}
			if (etDOB.text.isEmpty()) {
				etDOB.validationError { it.isNotEmpty() }
			}
			if (etGender.text.isEmpty()) {
				etGender.validationError { it.isNotEmpty() }
			}
		}
	}

	private fun getValidRequestModel() : ReqModelRegister {
		return with(mBinding) {
			ReqModelRegister(
				args.codeId,
				etName.text.toString(),
				etSurname.text.toString(),
				etSecondName.text.toString(),
				etDOB.text.toString().convertFrom { DatePatterns.DAY_MONTH_YEAR }.toServerDate(),
				1
			)
		}
	}

}

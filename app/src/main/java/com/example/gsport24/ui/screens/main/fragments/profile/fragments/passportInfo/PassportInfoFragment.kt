package com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gsport24.Application
import com.example.gsport24.databinding.FragmentPassportInfoBinding
import com.example.gsport24.databinding.LayoutPassportImageBinding
import com.example.gsport24.databinding.LayoutPassportImageUploadBinding
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.mvvm.ui.BaseRequestFragment
import com.example.gsport24.mvvm.vm.ViewCommand
import com.example.gsport24.root.ext.convertFrom
import com.example.gsport24.root.ext.convertServerDateTo
import com.example.gsport24.root.ext.convertTo
import com.example.gsport24.root.ext.toServerDate
import com.example.gsport24.root.utils.DatePatterns
import com.example.gsport24.root.utils.permissionChecker.permissionChecker
import com.example.gsport24.shared.DatePickerDialog
import com.example.gsport24.shared.LoadingButton
import com.example.gsport24.shared.ext.validationError
import com.example.gsport24.ui.commands.Commands
import com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.dialogs.ImagePickerBottomFragment
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import java.util.*

private const val IMAGE_PICKER_REQUEST_CODE = 741
private const val CAMERA_CAPTURE_REQUEST_CODE = 7410

class PassportInfoFragment : BaseRequestFragment<FragmentPassportInfoBinding, PassportInfoViewModel>() {

	private val userInfo:UserDomain? = Application.getInstance().getUserInfo()

	override val inflate : (LayoutInflater, ViewGroup?, Boolean) -> FragmentPassportInfoBinding
		get() = FragmentPassportInfoBinding::inflate

	override val viewModelType : Class<PassportInfoViewModel>
		get() = PassportInfoViewModel::class.java

	override fun initView(binding : FragmentPassportInfoBinding, viewModel : PassportInfoViewModel) {
		binding.apply {
			setViewModel(viewModel)
			initUserInfo()
			initAttachments()
			etGivenDate.setOnClickListener { showDatePicker { etGivenDate.setText(it.convertTo { DatePatterns.DAY_MONTH_YEAR }) } }
			tvSave.setOnClickListener {
				if (formIsInValid()) {
					(it as LoadingButton).isLoading(false)
					validateForm()
				} else {
					viewModel.changePassportInfo(
						etWhoGave.text.toString(),
						etPassportNumber.text.toString(),
						etGivenDate.text.toString().convertFrom { DatePatterns.DAY_MONTH_YEAR }.toServerDate()
					)
				}
			}
		}
	}

	private fun FragmentPassportInfoBinding.initUserInfo() {
		userInfo?.let {
			if (it.passportIssueDate.isNotEmpty()) {
				etGivenDate.setText(it.passportIssueDate.convertServerDateTo { DatePatterns.DAY_MONTH_YEAR })
			}
			etWhoGave.setText(it.passportIssuedBy)
			etPassportNumber.setText(it.passportNumber)
		}
	}

	private fun FragmentPassportInfoBinding.formIsInValid() : Boolean {
		return etGivenDate.text.isNullOrEmpty() ||
				etWhoGave.text.isNullOrEmpty() ||
				etPassportNumber.text.isNullOrEmpty()
	}

	private fun FragmentPassportInfoBinding.validateForm() {
		if (etGivenDate.text.isNullOrEmpty()) {
			etGivenDate.validationError { it.isNotEmpty() }
		}
		if (etPassportNumber.text.isNullOrEmpty()) {
			etPassportNumber.validationError { it.isNotEmpty() }
		}
		if (etWhoGave.text.isNullOrEmpty()) {
			etWhoGave.validationError { it.isNotEmpty() }
		}
	}

	private fun FragmentPassportInfoBinding.initAttachments() {
		userInfo?.let {
			it.attachments.forEach { photo ->
				val image = createImage(photo)
				fblPhotos.addView(image.root)
			}
			val image = LayoutPassportImageUploadBinding.inflate(layoutInflater)
			val lp = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
			lp.setMargins(0, 0, 10, 15)
			image.root.layoutParams = lp
			image.root.setOnClickListener {
				ImagePickerBottomFragment.show(childFragmentManager) {
					when (it) {
						is ImagePickerBottomFragment.Result.Camera -> openCamera()
						is ImagePickerBottomFragment.Result.Gallery -> openGallery()
					}
				}
			}
			fblPhotos.addView(image.root)
		}
	}

	private fun createImage(url : Any) : LayoutPassportImageBinding {
		return with(mBinding) {
			val image = LayoutPassportImageBinding.inflate(layoutInflater)
			val lp = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
			lp.setMargins(0, 0, 10, 15)
			image.root.layoutParams = lp
			Glide.with(requireContext())
				.load(url)
				.into(image.ivPassportImage)
			image.ivRemove.setOnClickListener {
				fblPhotos.removeView(image.root)
			}
			image
		}
	}

	private fun openCamera() {
		permissionChecker {
			permissions(android.Manifest.permission.CAMERA)
			check {
				doOnSuccess {
					val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
					startActivityForResult(intent, CAMERA_CAPTURE_REQUEST_CODE)
				}
				doOnFailure {
					showToast("Permission denied")
				}
				doOnNeverAsk {
					showToast("Permission denied")
				}
			}
		}
	}

	private fun openGallery() {
		val intent = Intent(Intent.ACTION_PICK)
		intent.type = "image/*"
		startActivityForResult(intent, IMAGE_PICKER_REQUEST_CODE)
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

	override fun proceedViewCommand(command : ViewCommand) {
		if (command is Commands.PassportInfoUpdated) {
			findNavController().popBackStack()
		}
	}

	override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
		if (requestCode == CAMERA_CAPTURE_REQUEST_CODE) {
			val bitmap = data?.extras?.get("data") as? Bitmap
			bitmap?.let {
				val image = createImage(it)
				mBinding.fblPhotos.addView(image.root, 0)
				mViewModel.createFile(it, requireContext().cacheDir)
			}
		}
		if (requestCode == IMAGE_PICKER_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				val input = requireActivity().contentResolver.openInputStream(data?.data!!)
				val bitmap = BitmapFactory.decodeStream(input)
				bitmap?.let {
					val image = createImage(it)
					mBinding.fblPhotos.addView(image.root, 0)
					mViewModel.createFile(it, requireContext().cacheDir)
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data)
	}
}

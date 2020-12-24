package com.example.gsport24.ui.screens.main.fragments.profile.fragments.passportInfo.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.FragmentManager
import com.example.gsport24.R
import com.example.gsport24.databinding.BottomFragmentImagePickerBinding
import com.example.gsport24.root.ext.getColorCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImagePickerBottomFragment private constructor() : BottomSheetDialogFragment() {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var mBinding: BottomFragmentImagePickerBinding

    override fun getTheme() : Int {
        return R.style.BottomSheetDialogTheme
    }

    private lateinit var callback: (Result) -> Unit

    sealed class Result {
        object Gallery : Result()
        object Camera : Result()
    }

    companion object {
        fun show(fragmentManager: FragmentManager, callback: (Result) -> Unit) = ImagePickerBottomFragment().apply {
            this.callback = callback
        }.show(fragmentManager, "ImagePicker")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dlg = super.onCreateDialog(savedInstanceState)
        dlg.setOnShowListener {
            it as BottomSheetDialog
            val view: FrameLayout = it.findViewById(R.id.design_bottom_sheet)!!
            view.background = context?.getColorCompat(android.R.color.transparent)?.toDrawable()
            mBottomSheetBehavior = BottomSheetBehavior.from(view)
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dlg
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = BottomFragmentImagePickerBinding.inflate(inflater, container, false).apply {
            camera.setOnClickListener { callback(Result.Camera);dismiss() }
            gallery.setOnClickListener { callback(Result.Gallery);dismiss() }
        }
        return mBinding.root
    }
}
package com.example.gsport24.ui.screens.main.adapters

import android.graphics.MaskFilter
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

object BindingAdapters {

	@JvmStatic
	@BindingAdapter("balance_visibility")
	fun changeBalanceVisibility(tv:TextView, maskFilter:LiveData<MaskFilter>){
		maskFilter.observeForever{ tv.paint.maskFilter = it }
	}

}
package com.example.gsport24.shared

import android.app.DatePickerDialog
import android.content.Context
import com.example.gsport24.R
import com.example.gsport24.root.ext.getColorCompat
import com.example.gsport24.root.ext.getDrawableCompat

class DatePickerDialog : DatePickerDialog {

	constructor(context : Context, listener : OnDateSetListener?, year : Int, month : Int, dayOfMonth : Int) : this(context, R.style.DatePickerTheme, listener, year, month, dayOfMonth)

	constructor(context : Context, themeResId : Int, listener : OnDateSetListener?, year : Int, monthOfYear : Int, dayOfMonth : Int) : super(context, themeResId, listener, year, monthOfYear, dayOfMonth) {
		window?.setBackgroundDrawable(context.getDrawableCompat(R.drawable.bcg_date_picker_dark_blue_rounded_12))
		getButton(BUTTON_POSITIVE)?.setTextColor(context.getColorCompat(R.color.colorAccent))
		getButton(BUTTON_NEGATIVE)?.setTextColor(context.getColorCompat(R.color.colorAccent))
	}

}
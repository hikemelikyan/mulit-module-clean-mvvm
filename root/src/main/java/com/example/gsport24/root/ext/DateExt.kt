package com.example.gsport24.root.ext

import com.example.gsport24.root.utils.DatePatterns.UTC
import java.text.SimpleDateFormat
import java.util.*

fun Date.toServerDate(pattern : String = UTC) : String {
	val sdf = SimpleDateFormat(pattern, Locale.UK)
	sdf.timeZone = TimeZone.getTimeZone("UTC")
	return try {
		sdf.format(this)
	} catch (e : Throwable) {
		e.printStackTrace()
		""
	}
}

fun String.convertServerDateTo(pattern : () -> String): String? {
	val simpleDateFormat = SimpleDateFormat(UTC, Locale.getDefault())
	simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

	val convertedDateFormat = SimpleDateFormat(pattern(), Locale.UK)
	convertedDateFormat.timeZone = TimeZone.getDefault()

	return simpleDateFormat.parse(this)?.let {
		convertedDateFormat.format(it)
	}
}

fun String?.convertFrom(pattern : () -> String) : Date {
	val sdf = SimpleDateFormat(pattern(), Locale.UK)
	sdf.timeZone = TimeZone.getTimeZone("UTC")
	return if (this != null) {
		sdf.parse(this)?.let {
			it
		} ?: throw Exception("Date $this is not valid")
	} else
		throw Exception("Date $this is not valid")
}

fun Date.convertTo(pattern : () -> String) : String {
	val sdf = SimpleDateFormat(pattern(), Locale.UK)
	return try {
		sdf.format(this)
	} catch (e : Throwable) {
		e.printStackTrace()
		""
	}
}
package com.example.gsport24.root.ext

import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.roundToLong

fun Double.format() : String {
	val formatter = NumberFormat.getInstance() as DecimalFormat
	val symbols = formatter.decimalFormatSymbols
	symbols.groupingSeparator = ','
	formatter.decimalFormatSymbols = symbols
	return "${formatter.format(this.roundToLong())} ₽"
}

fun Int.format() : String {
	val formatter = NumberFormat.getInstance() as DecimalFormat
	val symbols = formatter.decimalFormatSymbols
	symbols.groupingSeparator = ','
	formatter.decimalFormatSymbols = symbols
	return "${formatter.format(this.toLong())} ₽"
}

fun Long.format() : String {
	val formatter = NumberFormat.getInstance() as DecimalFormat
	val symbols = formatter.decimalFormatSymbols
	symbols.groupingSeparator = ','
	formatter.decimalFormatSymbols = symbols
	return "${formatter.format(this)} ₽"
}
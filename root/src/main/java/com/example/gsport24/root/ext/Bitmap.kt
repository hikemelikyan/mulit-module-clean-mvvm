package com.example.gsport24.root.ext

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray() : ByteArray {
	val byteArrayOutputStream = ByteArrayOutputStream()
	compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
	return byteArrayOutputStream.toByteArray()
}
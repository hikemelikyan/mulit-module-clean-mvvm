package com.example.gsport24.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CloudNotificationDomain(
	val notificationId: Int,
	val title: String,
	val body: String,
	var type: Int?,
): Parcelable
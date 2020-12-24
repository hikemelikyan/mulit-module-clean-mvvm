package com.example.gsport24.domain.entities

import android.os.Parcelable
import com.example.gsport24.data.root.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDomain(
	val id: Int,
	val type: Int,
	val firstName: String,
	val lastName: String,
	val secondName: String,
	val balance: Int,
	val city: String,
	val country: String,
	val dateOfBirth: String,
	val email: String,
	val gender: Int,
	val passportIssueDate: String,
	val passportIssuedBy: String,
	val passportNumber: String,
	val phone: String,
	val photo: String,
	val isUserConfirmed: Boolean,
	val attachments: List<String>
) : Entity ,Parcelable{
}
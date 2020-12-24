package com.example.gsport24.data.model.requestModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReqModelRegister(
    val id : Int,
    val firstName : String,
    val lastName : String,
    val fatherName : String,
    val dateOfBirth : String,
    val gender : Int,
    var deviceId : String? = null,
    var deviceToken : String? = null,
    val osType : Int = 1,
    var password : String? = null,
) : Parcelable
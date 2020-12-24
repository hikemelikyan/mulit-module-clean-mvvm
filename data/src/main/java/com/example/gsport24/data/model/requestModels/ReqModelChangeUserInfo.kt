package com.example.gsport24.data.model.requestModels

data class ReqModelChangeUserInfo(
    val dateOfBirth: String,
    val email: String,
    val fatherName: String,
    val firstName: String,
    val gender: Int,
    val lastName: String,
    val phone: String
)
package com.example.gsport24.data.model.responseModels

data class UserInfoResponseModel(
    val id: Int?,
    val type: Int?,
    val firstName: String?,
    val lastName: String?,
    val fatherName: String?,
    val balance: Int?,
    val city: String?,
    val country: String?,
    val dateOfBirth: String?,
    val email: String?,
    val gender: Int?,
    val passportIssueDate: String?,
    val passportIssuedBy: String?,
    val passportNumber: String?,
    val phone: String?,
    val photo: String?,
    val status: Int?,
    val attachments: List<String>?
)
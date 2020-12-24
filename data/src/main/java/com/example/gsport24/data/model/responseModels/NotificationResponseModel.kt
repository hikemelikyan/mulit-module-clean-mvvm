package com.example.gsport24.data.model.responseModels

data class NotificationResponseModel(
    val id: Int,
    val title: String,
    val description: String,
    val type: Int,
    val createdDate: String
)
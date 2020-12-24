package com.example.gsport24.data.model.responseModels.root

import retrofit2.Response

data class ResponseModel<T>(
    val success: Boolean,
    val data: T?,
    val messages: List<ResponseMessageModel>
)

data class ResponseMessageModel(
    val key: String,
    val message: String
)

typealias Response<T> = Response<ResponseModel<T>>
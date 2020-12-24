package com.example.gsport24.data.model.responseModels

data class TransactionListResponseModel(
	val amount:Double,
	val type:Int,
	val status: Int,
	val createdDate:String
)
package com.example.gsport24.data.model.requestModels

data class ReqModelLogin(
	val username:String,
	val password:String,
	val deviceId:String,
	val deviceToken:String,
	val osType:Int = 1,
)
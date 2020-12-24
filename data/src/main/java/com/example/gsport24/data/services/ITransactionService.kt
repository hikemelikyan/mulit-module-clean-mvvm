package com.example.gsport24.data.services

import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.TransactionListResponseModel
import com.example.gsport24.data.model.responseModels.root.PaginationResponseModel
import com.example.gsport24.data.model.responseModels.root.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ITransactionService {

	@POST("/api/Transaction/GetList")
	suspend fun getTransactionsList(@Body model : PaginationRequestModel) : Response<PaginationResponseModel<TransactionListResponseModel>>
}
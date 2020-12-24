package com.example.gsport24.data.repository.transaction

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.TransactionListResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.mapperBase.Mapper
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {

	suspend fun <T : Entity> getTransactionsList(model : PaginationRequestModel, mapper : Mapper<TransactionListResponseModel, T>) : Flow<PagingData<T>>

}
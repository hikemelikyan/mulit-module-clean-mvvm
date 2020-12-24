package com.example.gsport24.domain.useCase.transactionsHistory

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.PaymentMethodDomain
import com.example.gsport24.domain.entities.TransactionDomain
import kotlinx.coroutines.flow.Flow

interface GetTransactionsHistoryAndPaymentMethodsUseCase {
	suspend fun getPaymentMethods() : Flow<Result<List<PaymentMethodDomain>>>

	suspend fun getTransactionHistory(model:PaginationRequestModel) : Flow<Result<PagingData<TransactionDomain>>>
}
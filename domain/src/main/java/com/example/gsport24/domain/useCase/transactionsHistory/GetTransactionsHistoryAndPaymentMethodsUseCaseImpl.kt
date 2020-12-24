package com.example.gsport24.domain.useCase.transactionsHistory

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.repository.payment.PaymentRepository
import com.example.gsport24.data.repository.transaction.TransactionsRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.PaymentMethodDomain
import com.example.gsport24.domain.entities.TransactionDomain
import com.example.gsport24.domain.mappers.PaymentMethodMapper
import com.example.gsport24.domain.mappers.TransactionDomainMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsHistoryAndPaymentMethodsUseCaseImpl
@Inject
constructor(
	private val resultFactory : ResultFactory,
	private val paymentRepository : PaymentRepository,
	private val transactionsRepository : TransactionsRepository
) : GetTransactionsHistoryAndPaymentMethodsUseCase {

	override suspend fun getPaymentMethods() : Flow<Result<List<PaymentMethodDomain>>> {
		return resultFactory.getListResult { paymentRepository.getUserPaymentMethods(PaymentMethodMapper) }
	}

	override suspend fun getTransactionHistory(model : PaginationRequestModel) : Flow<Result<PagingData<TransactionDomain>>> {
		return resultFactory.getPagingResult { transactionsRepository.getTransactionsList(model,TransactionDomainMapper) }
	}

}
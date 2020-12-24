package com.example.gsport24.data.repository.transaction

import androidx.paging.PagingData
import androidx.paging.map
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.TransactionListResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.data.root.NetworkHelper
import com.example.gsport24.data.services.ITransactionService
import com.example.gsport24.root.mapperBase.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class TransactionsRepositoryImpl
@Inject
constructor(
	private val networkHelper : NetworkHelper,
	retrofit : Retrofit
) : TransactionsRepository {

	private val mService : ITransactionService = retrofit.create()

	override suspend fun <T : Entity> getTransactionsList(model:PaginationRequestModel,mapper : Mapper<TransactionListResponseModel, T>) : Flow<PagingData<T>> {
		val result : Flow<PagingData<TransactionListResponseModel>> = networkHelper.withModel(model) {
			paginate { mService.getTransactionsList(model) }
		}
		return result.map {
			it.map {
				mapper(it)
			}
		}
	}
}
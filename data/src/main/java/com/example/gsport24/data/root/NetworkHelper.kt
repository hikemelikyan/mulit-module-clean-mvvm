package com.example.gsport24.data.root

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.root.PaginationResponseModel
import com.example.gsport24.data.model.responseModels.root.Response
import kotlinx.coroutines.flow.Flow


interface NetworkHelper {

    @DslMarker
    annotation class ApiRequest

    @DslMarker
    annotation class PagingRequest

    @ApiRequest
    suspend fun <R> proceed(action: suspend () -> Response<R>): R?

    suspend fun <I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any> withModel(
        model:I,
        action: PagingBuilder<I,O,T>.() -> PagingBuilder<I,O,T>
    ): Flow<PagingData<T>>

    abstract class PagingBuilder<I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any>{

        @PagingRequest
        abstract fun paginate(action: suspend (I) -> Response<O>) : PagingBuilder<I,O,T>
        internal abstract fun proceed():Flow<PagingData<T>>
    }

}
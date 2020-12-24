package com.example.gsport24.data.root

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.root.PaginationResponseModel
import com.example.gsport24.data.model.responseModels.root.Response
import com.example.gsport24.root.di.ApplicationContext
import com.example.gsport24.root.utils.NetworkConnectivityChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class NetworkHelperImpl
@Inject
constructor(
    @ApplicationContext private val context : Context,
    private val connectivityChecker : NetworkConnectivityChecker
) : NetworkHelper {

	private val defaultInternalErrorMessage : String by lazy { context.getString(com.example.gsport24.data.R.string.default_internal_error_message) }
	private val defaultNetworkErrorMessage : String by lazy { context.getString(com.example.gsport24.data.R.string.default_network_error_message) }

	override suspend fun <R> proceed(action : suspend () -> Response<R>) : R? = callInternal { action() }

	override suspend fun <I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any> withModel(
        model : I,
        action : NetworkHelper.PagingBuilder<I, O, T>.() -> NetworkHelper.PagingBuilder<I, O, T>
    ) : Flow<PagingData<T>> {
		val pb : PagingBuilderImpl<I, O, T> = PagingBuilderImpl<I, O, T>().apply {
			action(this)
			withModel(model)
		}
		return pb.proceed().cachedIn(CoroutineScope(Dispatchers.IO))
	}

	private suspend fun <R> callInternal(action : suspend () -> Response<R>) : R? {
		return if (!connectivityChecker.isNetworkAvailable) {
			throw ApiException(UIState.NETWORK_ERROR, defaultNetworkErrorMessage, null)
		} else {
			val response = action()
			val responseBody = response.body()
			when {
				!response.isSuccessful -> throw ApiException(
                    UIState.SERVER_ERROR,
                    defaultInternalErrorMessage,
                    HttpException(response)
                )
				responseBody == null -> throw ApiException(
                    UIState.SERVER_ERROR,
                    defaultInternalErrorMessage,
                    null
                )
				!responseBody.success -> throw ApiException(
                    UIState.SERVER_ERROR,
                    response.body()?.messages?.get(0)?.message.toString(),
                    null
                )
				else -> responseBody.data
			}
		}
	}

	private inner class PagingBuilderImpl<I : PaginationRequestModel, O : PaginationResponseModel<T>, T : Any> : NetworkHelper.PagingBuilder<I, O, T>() {

		private var _model : I? = null
		private var _action : (suspend (I) -> Response<O>)? = null

		fun withModel(model : I) = run { _model = model }

		override fun paginate(action : suspend (I) -> Response<O>) = apply { _action = action }

		override fun proceed() : Flow<PagingData<T>> {
			return Pager(createDefaultPagingConfig()) {
				Paginator(Pair(_model!!, _action!!))
			}.flow.cachedIn(CoroutineScope(Dispatchers.IO))
		}

		private fun createDefaultPagingConfig() : PagingConfig {
			return PagingConfig(20, 5, false, 20)
		}

		private inner class Paginator<I : PaginationRequestModel, T : Any, O : PaginationResponseModel<T>>(
            private val modelAndRequestMediator : Pair<I, suspend (I) -> Response<O>>
        ) : PagingSource<Int, T>() {

			override suspend fun load(params : LoadParams<Int>) : LoadResult<Int, T> {
				try {
					val model = modelAndRequestMediator.first
					val request = modelAndRequestMediator.second
					model.count = params.loadSize
					model.page = params.key?.let { if (it == 0) 1 else it } ?: 1

					val result = callInternal { request(model) }

					return LoadResult.Page(
                        data = if (result?.data == null) emptyList() else result.data,
                        prevKey = if (model.page == 1) null else model.page - 1,
                        nextKey = when {
                            result?.data == null || result.pageCount == 0 || result.pageCount == model.page -> null
                            else -> {
                                model.page + 1
                            }
                        }
                    )
				} catch (e : Exception) {
					return LoadResult.Error(e)
				}
			}
		}

	}

}
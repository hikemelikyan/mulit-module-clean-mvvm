package com.example.gsport24.domain.useCase.menu

import androidx.paging.PagingData
import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.model.responseModels.NewsDetailsResponseModel
import com.example.gsport24.data.repository.news.NewsRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.domain.mappers.NewsResponseToDomainMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuUseCaseImpl
@Inject
constructor(
	private val newsRepository : NewsRepository,
	private val resultHandler : ResultFactory
) : GetMenuUseCase {

	override suspend fun getNewsList() : Flow<Result<PagingData<NewsDomain>>> {
		return resultHandler.getPagingResult { newsRepository.getList(PaginationRequestModel(), NewsResponseToDomainMapper) }
	}

	override suspend fun getDetails(id : Int) : Flow<Result<NewsDetailsResponseModel>> {
		return resultHandler.getResult { newsRepository.getById(id) }
	}
}
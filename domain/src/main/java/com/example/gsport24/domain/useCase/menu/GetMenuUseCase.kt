package com.example.gsport24.domain.useCase.menu

import androidx.paging.PagingData
import com.example.gsport24.data.model.responseModels.NewsDetailsResponseModel
import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.NewsDomain
import kotlinx.coroutines.flow.Flow

interface GetMenuUseCase {
	suspend fun getNewsList() : Flow<Result<PagingData<NewsDomain>>>
	suspend fun getDetails(id:Int) : Flow<Result<NewsDetailsResponseModel>>
}
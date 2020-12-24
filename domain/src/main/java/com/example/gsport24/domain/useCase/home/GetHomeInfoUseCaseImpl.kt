package com.example.gsport24.domain.useCase.home

import com.example.gsport24.data.model.requestModels.PaginationRequestModel
import com.example.gsport24.data.repository.category.CategoryRepository
import com.example.gsport24.data.repository.news.NewsRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.domain.mappers.CategoryResponseToDomainMapper
import com.example.gsport24.domain.mappers.NewsResponseToDomainMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeInfoUseCaseImpl
@Inject
constructor(
	private val newsRepository : NewsRepository,
	private val categoryRepository : CategoryRepository,
	private val resultHandler : ResultFactory
) : GetHomeInfoUseCase {

	override suspend fun getNewsList() : Flow<Result<List<NewsDomain>>> {
		return resultHandler.getListResult {
			newsRepository.getHomeList(PaginationRequestModel().apply {
				count = 5; page = 1
			}, NewsResponseToDomainMapper)
		}
	}

	override suspend fun getCategoriesList() : Flow<Result<List<CategoryDomain>>> {
		return resultHandler.getListResult { categoryRepository.getAll(CategoryResponseToDomainMapper) }
	}

	override suspend fun getBannersList() : Flow<Result<List<String>>> {
		return resultHandler.getListResult { getBanners() }
	}

	private suspend fun getBanners() : List<String> {
		delay(1000)
		return listOf(
			"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
			"https://static.toiimg.com/photo/72975551.cms",
			"https://www.w3schools.com/w3css/img_lights.jpg",
			"https://eskipaper.com/images/images-2.jpg",
			"https://latestauto20.com/wp-content/uploads/2020/07/photo-1494548162494-384bba4ab999.jpg"
		)
	}
}
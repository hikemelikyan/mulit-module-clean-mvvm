package com.example.gsport24.domain.mappers

import com.example.gsport24.data.model.responseModels.NewsResponseModel
import com.example.gsport24.domain.entities.NewsDomain
import com.example.gsport24.root.mapperBase.Mapper

object NewsResponseToDomainMapper : Mapper<NewsResponseModel, NewsDomain> {

	override fun invoke(input : NewsResponseModel) : NewsDomain {
		return with(input) {
			NewsDomain(id, title, photo, createdDate)
		}
	}
}
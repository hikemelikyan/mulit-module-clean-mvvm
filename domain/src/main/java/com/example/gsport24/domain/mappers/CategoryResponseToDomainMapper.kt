package com.example.gsport24.domain.mappers

import com.example.gsport24.data.model.responseModels.CategoryResponseModel
import com.example.gsport24.domain.entities.CategoryDomain
import com.example.gsport24.root.mapperBase.Mapper

object CategoryResponseToDomainMapper : Mapper<CategoryResponseModel, CategoryDomain> {

	override fun invoke(input : CategoryResponseModel) : CategoryDomain {
		return with(input) {
			CategoryDomain(
				id,
				name,
				photo,
				nextEvent
			)
		}
	}
}
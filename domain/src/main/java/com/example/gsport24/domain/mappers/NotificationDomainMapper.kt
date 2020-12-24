package com.example.gsport24.domain.mappers

import com.example.gsport24.data.model.responseModels.NotificationResponseModel
import com.example.gsport24.domain.entities.NotificationDomain
import com.example.gsport24.root.mapperBase.Mapper

object NotificationDomainMapper : Mapper<NotificationResponseModel,NotificationDomain> {

	override fun invoke(input : NotificationResponseModel) : NotificationDomain {
		return with(input){
			NotificationDomain(
				id,
				title,
				description,
				createdDate,
				type
			)
		}
	}
}
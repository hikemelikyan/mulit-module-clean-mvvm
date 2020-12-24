package com.example.gsport24.domain.mappers

import com.example.gsport24.data.enums.UserStatus
import com.example.gsport24.data.model.responseModels.UserInfoResponseModel
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.root.mapperBase.Mapper

object UserDomainMapper : Mapper<UserInfoResponseModel, UserDomain> {

	override fun invoke(input : UserInfoResponseModel) : UserDomain {
		return with(input) {
			UserDomain(
				id ?: -1,
				type ?: -1,
				firstName ?: "",
				lastName ?: "",
				fatherName ?: "",
				balance ?: -1,
				city ?: "",
				country ?: "",
				dateOfBirth ?: "",
				email ?: "",
				gender ?: -1,
				passportIssueDate ?: "",
				passportIssuedBy ?: "",
				passportNumber ?: "",
				phone ?: "",
				photo ?: "",
				status == UserStatus.Confirmed.value,
				attachments ?: emptyList()
			)
		}
	}
}
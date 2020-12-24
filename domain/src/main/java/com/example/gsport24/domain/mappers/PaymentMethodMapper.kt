package com.example.gsport24.domain.mappers

import com.example.gsport24.data.model.responseModels.PaymentMethodResponseModel
import com.example.gsport24.domain.entities.PaymentMethodDomain
import com.example.gsport24.root.mapperBase.Mapper

object PaymentMethodMapper : Mapper<PaymentMethodResponseModel, PaymentMethodDomain> {

	override fun invoke(input : PaymentMethodResponseModel) : PaymentMethodDomain {
		return with(input) {
			PaymentMethodDomain(
				cardNumbers,
				givenDate,
				userInfo
			)
		}
	}
}
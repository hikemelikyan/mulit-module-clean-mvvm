package com.example.gsport24.data.repository.payment

import com.example.gsport24.data.model.responseModels.PaymentMethodResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.mapperBase.Mapper
import javax.inject.Inject

class PaymentRepositoryImpl
@Inject
constructor(

) : PaymentRepository {

	override suspend fun <T : Entity> getUserPaymentMethods(mapper : Mapper<PaymentMethodResponseModel, T>) : List<T> {
		return listOf(
			mapper(PaymentMethodResponseModel("1519********7484", "12/24", "Hayk Melikyan")),
			mapper(PaymentMethodResponseModel("4523********7777", "08/24", "User Useryan")),
			mapper(PaymentMethodResponseModel("3479********7788", "03/22", "Exo Gasltyan")),
			mapper(PaymentMethodResponseModel("6648********2354", "12/21", "Hayk Melikyan")),
			mapper(PaymentMethodResponseModel("8215********2156", "12/24", "Hayk Melikyan"))
		)
	}
}
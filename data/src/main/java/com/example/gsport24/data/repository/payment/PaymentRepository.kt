package com.example.gsport24.data.repository.payment

import com.example.gsport24.data.model.responseModels.PaymentMethodResponseModel
import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.mapperBase.Mapper

interface PaymentRepository {

	suspend fun <T:Entity> getUserPaymentMethods(mapper : Mapper<PaymentMethodResponseModel,T>) : List<T>?

}
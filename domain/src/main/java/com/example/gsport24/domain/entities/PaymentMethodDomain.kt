package com.example.gsport24.domain.entities

import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.utils.DifItem

data class PaymentMethodDomain(
	val cardNumbers : String,
	val givenDate : String,
	val userInfo : String
) : Entity,DifItem<PaymentMethodDomain> {

	override fun areItemsTheSame(second : PaymentMethodDomain) : Boolean {
		return cardNumbers == second.cardNumbers
	}

	override fun areContentsTheSame(second : PaymentMethodDomain) : Boolean {
		return givenDate == second.givenDate &&
				userInfo == second.userInfo
	}
}
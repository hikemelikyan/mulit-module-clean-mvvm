package com.example.gsport24.domain.entities

import com.example.gsport24.data.root.Entity
import com.example.gsport24.root.utils.DifItem

data class TransactionDomain(
	val amount : Double,
	val isDeposit : Boolean,
	val isCashOut : Boolean,
	val isBonus : Boolean,
	val isBetting : Boolean,
	val isConfirmed:Boolean,
	val date : String
): Entity, DifItem<TransactionDomain> {

	override fun areItemsTheSame(second : TransactionDomain) : Boolean {
		return amount == second.amount
	}

	override fun areContentsTheSame(second : TransactionDomain) : Boolean {
		return date == second.date &&
				isDeposit == second.isDeposit &&
				isCashOut == second.isCashOut &&
				isBonus == second.isBonus &&
				isBetting == second.isBetting &&
				isConfirmed == second.isConfirmed
	}
}
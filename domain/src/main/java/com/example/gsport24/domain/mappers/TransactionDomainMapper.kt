package com.example.gsport24.domain.mappers

import com.example.gsport24.data.enums.TransactionStatus
import com.example.gsport24.data.enums.TransactionType
import com.example.gsport24.data.model.responseModels.TransactionListResponseModel
import com.example.gsport24.domain.entities.TransactionDomain
import com.example.gsport24.root.mapperBase.Mapper

object TransactionDomainMapper : Mapper<TransactionListResponseModel,TransactionDomain> {

	override fun invoke(input : TransactionListResponseModel) : TransactionDomain {
		return with(input){
			TransactionDomain(
				amount,
				isDeposit = type == TransactionType.Deposit.value,
				isBetting = type == TransactionType.Betting.value,
				isBonus = type == TransactionType.Bonus.value,
				isCashOut = type == TransactionType.CashOut.value,
				isConfirmed = status == TransactionStatus.Confirmed.value,
				date = createdDate
			)
		}
	}
}
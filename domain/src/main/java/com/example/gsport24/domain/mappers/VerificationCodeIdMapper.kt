package com.example.gsport24.domain.mappers

import com.example.gsport24.domain.entities.SmsCodeIdDomain
import com.example.gsport24.root.mapperBase.Mapper

object VerificationCodeIdMapper: Mapper<Int?, SmsCodeIdDomain> {

	override fun invoke(input : Int?) : SmsCodeIdDomain {
		return SmsCodeIdDomain(input ?: 0)
	}
}
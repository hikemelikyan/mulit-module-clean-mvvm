package com.example.gsport24.domain.useCase.passportInfo

import com.example.gsport24.data.repository.user.UserRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.domain.mappers.UserDomainMapper
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class UpdatePassportInfoUseCaseImpl
@Inject
constructor(
	private val userRepository : UserRepository,
	private val resultFactory : ResultFactory
) : UpdatePassportInfoUseCase {

	override suspend fun changePassportInfo(body : List<MultipartBody.Part>) : Flow<Result<UserDomain>> {
		return resultFactory.getResult { userRepository.changePassport(body,UserDomainMapper) }
	}

}
package com.example.gsport24.domain.useCase.profile.get

import com.example.gsport24.data.repository.user.UserRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.domain.mappers.UserDomainMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserPersonalInformationUseCaseImpl
@Inject
constructor(
	private val userRepository : UserRepository,
	private val resultFactory : ResultFactory,
) : GetUserPersonalInformationUseCase {

	override suspend fun getUserInfo() : Flow<Result<UserDomain>> {
		return resultFactory.getResult { userRepository.getProfileInfo(UserDomainMapper) }
	}

	override suspend fun getUserInfoInternal() : Flow<Result<String>> {
		return resultFactory.getResult { userRepository.getProfileInfoInternal() }
	}

}
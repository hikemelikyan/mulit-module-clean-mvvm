package com.example.gsport24.domain.useCase.profile.update

import com.example.gsport24.data.model.requestModels.ReqModelChangeUserInfo
import com.example.gsport24.data.repository.user.UserRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import com.example.gsport24.domain.entities.UserDomain
import com.example.gsport24.domain.mappers.UserDomainMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserInfoUseCaseImpl
@Inject
constructor(
	private val userRepository : UserRepository,
	private val resultFactory : ResultFactory
) : UpdateUserInfoUseCase {

	override suspend fun updateUserInfo(model : ReqModelChangeUserInfo) : Flow<Result<UserDomain>> {
		return resultFactory.getResult { userRepository.updatePersonalInfo(model, UserDomainMapper) }
	}

}
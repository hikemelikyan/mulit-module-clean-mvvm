package com.example.gsport24.domain.useCase.userRegistration

import com.example.gsport24.data.model.requestModels.ReqModelRegister
import com.example.gsport24.data.repository.auth.AuthRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRegistrationUseCaseImpl
@Inject
constructor(
	private val authRepository : AuthRepository,
	private val resultFactory : ResultFactory
) : UserRegistrationUseCase {

	override suspend fun registerUser(model : ReqModelRegister) : Flow<Result<String>> {
		return resultFactory.getResult { authRepository.registerUser(model) }
	}
}
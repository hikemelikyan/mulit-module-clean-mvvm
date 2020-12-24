package com.example.gsport24.domain.useCase.login

import com.example.gsport24.data.model.requestModels.ReqModelLogin
import com.example.gsport24.data.repository.auth.AuthRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInUseCaseImpl
@Inject
constructor(
	private val authRepository : AuthRepository,
	private val resultFactory : ResultFactory
):LogInUseCase {

	override suspend fun logIn(model : ReqModelLogin) : Flow<Result<String>> {
		return resultFactory.getResult { authRepository.login(model) }
	}
}
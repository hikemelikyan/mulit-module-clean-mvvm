package com.example.gsport24.domain.useCase.login

import com.example.gsport24.data.model.requestModels.ReqModelLogin
import com.example.gsport24.data.root.Result
import kotlinx.coroutines.flow.Flow

interface LogInUseCase {

	suspend fun logIn(model:ReqModelLogin) : Flow<Result<String>>

}
package com.example.gsport24.domain.useCase.profile.get

import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.UserDomain
import kotlinx.coroutines.flow.Flow

interface GetUserPersonalInformationUseCase {

	suspend fun getUserInfo() : Flow<Result<UserDomain>>

	suspend fun getUserInfoInternal() : Flow<Result<String>>
}
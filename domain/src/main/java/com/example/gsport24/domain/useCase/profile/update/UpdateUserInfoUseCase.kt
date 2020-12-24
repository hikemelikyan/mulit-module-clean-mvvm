package com.example.gsport24.domain.useCase.profile.update

import com.example.gsport24.data.model.requestModels.ReqModelChangeUserInfo
import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.UserDomain
import kotlinx.coroutines.flow.Flow

interface UpdateUserInfoUseCase {

	suspend fun updateUserInfo(model:ReqModelChangeUserInfo): Flow<Result<UserDomain>>

}
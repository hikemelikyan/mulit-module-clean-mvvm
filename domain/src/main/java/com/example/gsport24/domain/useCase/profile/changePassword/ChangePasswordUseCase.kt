package com.example.gsport24.domain.useCase.profile.changePassword

import com.example.gsport24.data.root.Result
import kotlinx.coroutines.flow.Flow

interface ChangePasswordUseCase {

	suspend fun changePassword(oldPassword : String, newPassword : String) : Flow<Result<Boolean>>
}
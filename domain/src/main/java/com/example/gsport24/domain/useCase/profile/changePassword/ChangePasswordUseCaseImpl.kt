package com.example.gsport24.domain.useCase.profile.changePassword

import com.example.gsport24.data.model.requestModels.ReqModelChangePassword
import com.example.gsport24.data.repository.user.UserRepository
import com.example.gsport24.data.root.Result
import com.example.gsport24.data.root.ResultFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCaseImpl
@Inject
constructor(
	private val resultFactory : ResultFactory,
	private val userRepository : UserRepository
) : ChangePasswordUseCase {

	override suspend fun changePassword(oldPassword : String, newPassword : String) : Flow<Result<Boolean>> {
		return resultFactory.getResult { userRepository.changePassword(ReqModelChangePassword(oldPassword, newPassword)) }
	}
}
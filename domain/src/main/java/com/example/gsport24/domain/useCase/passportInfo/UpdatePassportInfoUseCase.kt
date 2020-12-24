package com.example.gsport24.domain.useCase.passportInfo

import com.example.gsport24.data.root.Result
import com.example.gsport24.domain.entities.UserDomain
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UpdatePassportInfoUseCase {

	suspend fun changePassportInfo(body : List<MultipartBody.Part>) : Flow<Result<UserDomain>>

}
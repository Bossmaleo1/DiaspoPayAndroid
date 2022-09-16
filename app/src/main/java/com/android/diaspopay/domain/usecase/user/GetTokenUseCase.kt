package com.android.diaspopay.domain.usecase.user

import com.android.diaspopay.data.model.api.ApiTokenResponse
import com.android.diaspopay.data.util.Resource
import com.android.diaspopay.domain.repository.UserRepository

class GetTokenUseCase(private val userRepository: UserRepository) {
    suspend fun execute(userName: String, password: String): Resource<ApiTokenResponse> {
        return userRepository.getToken(userName, password)
    }
}
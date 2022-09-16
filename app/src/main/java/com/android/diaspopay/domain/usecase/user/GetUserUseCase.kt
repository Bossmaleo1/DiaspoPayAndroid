package com.android.diaspopay.domain.usecase.user

import com.android.diaspopay.data.model.api.ApiUserResponse
import com.android.diaspopay.data.util.Resource
import com.android.diaspopay.domain.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(userName: String, token: String): Resource<ApiUserResponse> {
        return userRepository.getUsers(userName, token)
    }
}
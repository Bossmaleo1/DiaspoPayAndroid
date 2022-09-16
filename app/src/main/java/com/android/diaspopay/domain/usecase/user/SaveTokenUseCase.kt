package com.android.diaspopay.domain.usecase.user

import com.android.diaspopay.data.model.dataRoom.TokenRoom
import com.android.diaspopay.domain.repository.UserRepository

class SaveTokenUseCase(private val userRepository: UserRepository) {
    suspend fun execute(token: TokenRoom) = userRepository.saveToken(token)
}
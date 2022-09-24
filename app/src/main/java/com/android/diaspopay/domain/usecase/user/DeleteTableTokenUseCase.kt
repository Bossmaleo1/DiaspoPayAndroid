package com.android.diaspopay.domain.usecase.user

import com.android.diaspopay.domain.repository.UserRepository

class DeleteTableTokenUseCase(private val userRepository: UserRepository) {
    suspend fun execute() = userRepository.deleteTokenTable()
}
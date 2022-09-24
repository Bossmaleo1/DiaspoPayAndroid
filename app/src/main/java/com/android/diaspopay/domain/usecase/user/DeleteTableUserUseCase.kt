package com.android.diaspopay.domain.usecase.user

import com.android.diaspopay.data.model.dataRoom.UserRoom
import com.android.diaspopay.domain.repository.UserRepository

class DeleteTableUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute() = userRepository.deleteUserTable()
}
package com.android.diaspopay.domain.usecase.user

import com.android.diaspopay.data.model.dataRoom.UserRoom
import com.android.diaspopay.domain.repository.UserRepository

class DeleteSavedUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute(user: UserRoom) = userRepository.deleteUser(user)
}
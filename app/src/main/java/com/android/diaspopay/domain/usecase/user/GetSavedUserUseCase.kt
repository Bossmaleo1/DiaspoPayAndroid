package com.android.diaspopay.domain.usecase.user

import com.android.diaspopay.data.model.dataRoom.UserRoom
import com.android.diaspopay.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetSavedUserUseCase(private val userRepository: UserRepository) {
    fun execute(userToken: String): Flow<UserRoom> {
        return userRepository.getSavedUser(userToken)
    }
}
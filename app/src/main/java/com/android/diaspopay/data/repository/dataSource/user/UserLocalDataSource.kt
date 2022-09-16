package com.android.diaspopay.data.repository.dataSource.user

import com.android.diaspopay.data.model.dataRoom.TokenRoom
import com.android.diaspopay.data.model.dataRoom.UserRoom
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUserToDB(user: UserRoom)
    suspend fun saveTokenToDB(token: TokenRoom)
    fun getSavedToken(): Flow<TokenRoom>
    fun getSavedUsers(): Flow<List<UserRoom>>
    fun getSavedUser(userToken: String): Flow<UserRoom>
    suspend fun deleteUserFromDB(user: UserRoom)
}
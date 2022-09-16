package com.android.diaspopay.presentation.di

import com.android.diaspopay.data.repository.UserRepositoryImpl
import com.android.diaspopay.data.repository.dataSource.user.UserLocalDataSource
import com.android.diaspopay.data.repository.dataSource.user.UserRemoteDataSource
import com.android.diaspopay.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource, userLocalDataSource)
    }
}
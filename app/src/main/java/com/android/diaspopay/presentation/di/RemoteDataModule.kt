package com.android.diaspopay.presentation.di

import com.android.diaspopay.data.api.service.UserAPIService
import com.android.diaspopay.data.repository.dataSource.user.UserRemoteDataSource
import com.android.diaspopay.data.repository.dataSourceImpl.UserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        userAPIService: UserAPIService
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userAPIService)
    }
}
package com.android.diaspopay.presentation.di

import com.android.diaspopay.data.repository.MeansPaymentRepositoryImpl
import com.android.diaspopay.data.repository.TransferRepositoryImpl
import com.android.diaspopay.data.repository.UserRepositoryImpl
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentLocalDataSource
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentRemoteDataSource
import com.android.diaspopay.data.repository.dataSource.transfer.TransferLocalDataSource
import com.android.diaspopay.data.repository.dataSource.transfer.TransferRemoteDataSource
import com.android.diaspopay.data.repository.dataSource.user.UserLocalDataSource
import com.android.diaspopay.data.repository.dataSource.user.UserRemoteDataSource
import com.android.diaspopay.domain.repository.MeansPaymentRepository
import com.android.diaspopay.domain.repository.TransferRepository
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

    @Singleton
    @Provides
    fun provideMeansPaymentRepository(
        meansPaymentLocalDataSource: MeansPaymentLocalDataSource,
        meansPaymentRemoteDataSource: MeansPaymentRemoteDataSource
    ): MeansPaymentRepository {
        return MeansPaymentRepositoryImpl(meansPaymentLocalDataSource,meansPaymentRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideTransferRepository(
        transferLocalDataSource: TransferLocalDataSource,
        transferRemoteDataSource: TransferRemoteDataSource
    ): TransferRepository {
        return TransferRepositoryImpl(transferLocalDataSource,transferRemoteDataSource)
    }
}
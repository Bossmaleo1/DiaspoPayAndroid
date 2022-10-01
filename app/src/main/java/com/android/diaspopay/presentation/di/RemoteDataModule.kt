package com.android.diaspopay.presentation.di

import com.android.diaspopay.data.api.service.MeansPaymentAPIService
import com.android.diaspopay.data.api.service.TransferAPIService
import com.android.diaspopay.data.api.service.UserAPIService
import com.android.diaspopay.data.repository.TransferRepositoryImpl
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentRemoteDataSource
import com.android.diaspopay.data.repository.dataSource.transfer.TransferRemoteDataSource
import com.android.diaspopay.data.repository.dataSource.user.UserRemoteDataSource
import com.android.diaspopay.data.repository.dataSourceImpl.meanspayment.MeansPaymentRemoteDataSourceImpl
import com.android.diaspopay.data.repository.dataSourceImpl.transfer.TransferRemoteDataSourceImpl
import com.android.diaspopay.data.repository.dataSourceImpl.user.UserRemoteDataSourceImpl
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

    @Singleton
    @Provides
    fun provideMeansPaymentRemoteDataSource(
        meansPaymentAPIService: MeansPaymentAPIService
    ): MeansPaymentRemoteDataSource {
        return MeansPaymentRemoteDataSourceImpl(meansPaymentAPIService)
    }

    @Singleton
    @Provides
    fun provideTransferRemoteDataSourceImpl(
        transferAPIService: TransferAPIService
    ): TransferRemoteDataSource {
        return TransferRemoteDataSourceImpl(transferAPIService)
    }

}
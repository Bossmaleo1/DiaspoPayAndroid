package com.android.diaspopay.presentation.di

import com.android.diaspopay.data.db.dao.MeansPaymentDAO
import com.android.diaspopay.data.db.dao.TransferDAO
import com.android.diaspopay.data.db.dao.UserDAO
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentLocalDataSource
import com.android.diaspopay.data.repository.dataSource.transfer.TransferLocalDataSource
import com.android.diaspopay.data.repository.dataSource.user.UserLocalDataSource
import com.android.diaspopay.data.repository.dataSourceImpl.meanspayment.MeansPaymentLocalDataSourceImpl
import com.android.diaspopay.data.repository.dataSourceImpl.transfer.TransferLocalDataSourceImpl
import com.android.diaspopay.data.repository.dataSourceImpl.user.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(userDAO: UserDAO): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDAO)
    }

    @Singleton
    @Provides
    fun provideTransferLocalDataSource(transferDAO: TransferDAO): TransferLocalDataSource {
        return TransferLocalDataSourceImpl(transferDAO)
    }

    @Singleton
    @Provides
    fun provideMeansPaymentLocalDataSource(meansPaymentDAO: MeansPaymentDAO): MeansPaymentLocalDataSource {
        return MeansPaymentLocalDataSourceImpl(meansPaymentDAO)
    }
}
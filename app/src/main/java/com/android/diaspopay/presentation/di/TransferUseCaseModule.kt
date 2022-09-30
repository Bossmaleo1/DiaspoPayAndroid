package com.android.diaspopay.presentation.di

import com.android.diaspopay.domain.repository.TransferRepository
import com.android.diaspopay.domain.usecase.transfer.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TransferUseCaseModule {

    @Singleton
    @Provides
    fun provideTransferUseCase(
        transferRepository: TransferRepository
    ): GetTransferUseCase {
        return GetTransferUseCase(transferRepository)
    }

    @Singleton
    @Provides
    fun provideSaveTransferUseCase(
        transferRepository: TransferRepository
    ): SaveTransferUseCase {
        return SaveTransferUseCase(transferRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedTransferUseCase(
        transferRepository: TransferRepository
    ): GetSavedTransferUseCase {
        return GetSavedTransferUseCase(transferRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteTableTransferUseCase(
        transferRepository: TransferRepository
    ): DeleteTableTransferUseCase {
        return DeleteTableTransferUseCase(transferRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteTransferUseCase(
        transferRepository: TransferRepository
    ): DeleteSavedTransferUseCase {
        return DeleteSavedTransferUseCase(transferRepository)
    }


}
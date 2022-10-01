package com.android.diaspopay.presentation.di

import com.android.diaspopay.domain.repository.MeansPaymentRepository
import com.android.diaspopay.domain.usecase.meanspayment.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MeansPaymentUseCaseModule {
    @Singleton
    @Provides
    fun provideMeansPaymentUseCase(
        meansPaymentRepository: MeansPaymentRepository
    ): GetMeansPaymentUseCase {
        return GetMeansPaymentUseCase(meansPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideSaveMeansPaymentUseCase(
        meansPaymentRepository: MeansPaymentRepository
    ): SaveMeansPaymentUseCase {
        return SaveMeansPaymentUseCase(meansPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedMeansPaymentUseCase(
        meansPaymentRepository: MeansPaymentRepository
    ): GetSavedMeansPaymentUseCase {
        return GetSavedMeansPaymentUseCase(meansPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteTableMeansPaymentUseCase(
        meansPaymentRepository: MeansPaymentRepository
    ): DeleteTableMeansPaymentUseCase {
        return DeleteTableMeansPaymentUseCase(meansPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSavedMeansPaymentUseCase(
        meansPaymentRepository: MeansPaymentRepository
    ): DeleteSavedMeansPaymentUseCase {
        return DeleteSavedMeansPaymentUseCase(meansPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateSavedTransferUseCase(
        meansPaymentRepository: MeansPaymentRepository
    ): UpdateSavedMeansPaymentUseCase {
        return UpdateSavedMeansPaymentUseCase(meansPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideSearchMeansPaymentUseCase(
        meansPaymentRepository: MeansPaymentRepository
    ): GetSearchMeansPaymentUseCase {
        return GetSearchMeansPaymentUseCase(meansPaymentRepository)
    }

}
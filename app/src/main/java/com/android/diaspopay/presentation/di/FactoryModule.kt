package com.android.diaspopay.presentation.di

import android.app.Application
import com.android.diaspopay.domain.repository.MeansPaymentRepository
import com.android.diaspopay.domain.usecase.meanspayment.*
import com.android.diaspopay.domain.usecase.transfer.*
import com.android.diaspopay.domain.usecase.user.*
import com.android.diaspopay.presentation.viewModel.drop.DropViewModelFactory
import com.android.diaspopay.presentation.viewModel.meansPayment.MeansPaymentViewModelFactory
import com.android.diaspopay.presentation.viewModel.transfer.TransferViewModelFactory
import com.android.diaspopay.presentation.viewModel.user.UserViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideUserViewModelFactory(
        application: Application,
        getUserUseCase: GetUserUseCase,
        getTokenUseCase: GetTokenUseCase,
        saveUserUseCase: SaveUserUseCase,
        saveTokenUseCase: SaveTokenUseCase,
        getSavedUserUseCase: GetSavedUserUseCase,
        getSavedTokenUseCase: GetSavedTokenUseCase,
        deleteSavedUserUseCase: DeleteSavedUserUseCase
    ): UserViewModelFactory {
        return UserViewModelFactory(
            application,
            getUserUseCase,
            getTokenUseCase,
            saveUserUseCase,
            saveTokenUseCase,
            getSavedUserUseCase,
            getSavedTokenUseCase,
            deleteSavedUserUseCase
        )
    }

    @Singleton
    @Provides
    fun provideDropViewModelFactory(
        application: Application,
        deleteTableUserUseCase: DeleteTableUserUseCase,
        deleteTableTokenUseCase: DeleteTableTokenUseCase
    ): DropViewModelFactory {
        return DropViewModelFactory(
            application,
            deleteTableUserUseCase,
            deleteTableTokenUseCase
        )
    }

    @Singleton
    @Provides
    fun provideTransferViewModelFactory(
        application: Application,
        getTransferUseCase: GetTransferUseCase,
        getSavedTransferUseCase: GetSavedTransferUseCase,
       saveTransferUseCase: SaveTransferUseCase,
        updateSavedTransferUseCase: UpdateSavedTransferUseCase,
        deleteSavedTransferUseCase: DeleteSavedTransferUseCase,
       deleteTableTransferUseCase: DeleteTableTransferUseCase
    ): TransferViewModelFactory {
        return TransferViewModelFactory(
            application,
            getTransferUseCase,
            getSavedTransferUseCase,
            saveTransferUseCase,
            updateSavedTransferUseCase,
            deleteSavedTransferUseCase,
            deleteTableTransferUseCase
        )
    }

    @Singleton
    @Provides
    fun provideMeansPaymentViewModelFactory(
          application: Application,
          getMeansPaymentUseCase: GetMeansPaymentUseCase,
          getSavedMeansPaymentUseCase: GetSavedMeansPaymentUseCase,
          getSearchMeansPaymentUseCase: GetSearchMeansPaymentUseCase,
          savedMeansPaymentUseCase: SaveMeansPaymentUseCase,
          updateSavedMeansPaymentUseCase: UpdateSavedMeansPaymentUseCase,
          deleteSavedMeansPaymentUseCase: DeleteSavedMeansPaymentUseCase,
          deleteTableMeansPayment: DeleteTableMeansPayment
    ): MeansPaymentViewModelFactory {
        return MeansPaymentViewModelFactory(
            application,
            getMeansPaymentUseCase,
            getSavedMeansPaymentUseCase,
            getSearchMeansPaymentUseCase,
            savedMeansPaymentUseCase,
            updateSavedMeansPaymentUseCase,
            deleteSavedMeansPaymentUseCase,
            deleteTableMeansPayment
        )
    }
}
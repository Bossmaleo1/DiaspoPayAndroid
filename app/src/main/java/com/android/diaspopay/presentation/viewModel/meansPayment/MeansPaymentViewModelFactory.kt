package com.android.diaspopay.presentation.viewModel.meansPayment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.diaspopay.domain.usecase.meanspayment.*

class MeansPaymentViewModelFactory(
    private val app: Application,
    private val getMeansPaymentUseCase: GetMeansPaymentUseCase,
    private val getSavedMeansPaymentUseCase: GetSavedMeansPaymentUseCase,
    private val getSearchMeansPaymentUseCase: GetSearchMeansPaymentUseCase,
    private val savedMeansPaymentUseCase: SaveMeansPaymentUseCase,
    private val updateSavedMeansPaymentUseCase: UpdateSavedMeansPaymentUseCase,
    private val deleteSavedMeansPaymentUseCase: DeleteSavedMeansPaymentUseCase,
    private val deleteTableMeansPayment: DeleteTableMeansPayment
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MeansPaymentViewModel(
            app,
            getMeansPaymentUseCase,
            getSavedMeansPaymentUseCase,
            getSearchMeansPaymentUseCase,
            savedMeansPaymentUseCase,
            updateSavedMeansPaymentUseCase,
            deleteSavedMeansPaymentUseCase,
            deleteTableMeansPayment
        ) as T
    }
}
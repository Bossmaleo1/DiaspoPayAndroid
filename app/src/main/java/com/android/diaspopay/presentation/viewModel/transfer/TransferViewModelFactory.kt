package com.android.diaspopay.presentation.viewModel.transfer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.diaspopay.domain.usecase.transfer.*

class TransferViewModelFactory(
    private val app: Application,
    private val getTransferUseCase: GetTransferUseCase,
    private val getSavedTransferUseCase: GetSavedTransferUseCase,
    private val saveTransferUseCase: SaveTransferUseCase,
    private val updateSavedTransferUseCase: UpdateSavedTransferUseCase,
    private val deleteSavedTransferUseCase: DeleteSavedTransferUseCase,
    private val deleteTableTransferUseCase: DeleteTableTransferUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransferViewModel(
            app,
            getTransferUseCase,
            getSavedTransferUseCase,
            saveTransferUseCase,
            updateSavedTransferUseCase,
            deleteSavedTransferUseCase,
            deleteTableTransferUseCase
        ) as T
    }
}
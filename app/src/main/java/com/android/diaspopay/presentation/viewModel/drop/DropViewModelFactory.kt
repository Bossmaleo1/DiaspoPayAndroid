package com.android.diaspopay.presentation.viewModel.drop

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.diaspopay.domain.usecase.meanspayment.DeleteTableMeansPaymentUseCase
import com.android.diaspopay.domain.usecase.transfer.DeleteTableTransferUseCase
import com.android.diaspopay.domain.usecase.user.DeleteTableTokenUseCase
import com.android.diaspopay.domain.usecase.user.DeleteTableUserUseCase

class DropViewModelFactory(
    private val app: Application,
    private val deleteTableUserUseCase: DeleteTableUserUseCase,
    private val deleteTableTokenUseCase: DeleteTableTokenUseCase,
    private val deleteTableTransferUseCase: DeleteTableTransferUseCase,
    private val deleteTableMeansPaymentUseCase: DeleteTableMeansPaymentUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DropViewModel(
            app,
            deleteTableUserUseCase,
            deleteTableTokenUseCase,
            deleteTableTransferUseCase,
            deleteTableMeansPaymentUseCase
        ) as T
    }

}
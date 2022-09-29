package com.android.diaspopay.domain.usecase.transfer

import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow

class GetSavedTransferUseCase(private val transferRepository: TransferRepository) {
    fun execute(): Flow<List<TransferRoom>> {
        return transferRepository.getSavedTransfer()
    }
}
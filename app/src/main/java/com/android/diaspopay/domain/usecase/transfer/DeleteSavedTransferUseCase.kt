package com.android.diaspopay.domain.usecase.transfer

import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.domain.repository.TransferRepository

class DeleteSavedTransferUseCase(private val transferRepository: TransferRepository) {
    suspend fun execute(transfer: TransferRoom) = transferRepository.deleteTransfer(transfer)
}
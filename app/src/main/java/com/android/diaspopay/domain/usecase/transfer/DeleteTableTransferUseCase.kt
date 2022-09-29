package com.android.diaspopay.domain.usecase.transfer

import com.android.diaspopay.domain.repository.TransferRepository

class DeleteTableTransferUseCase(private val transferRepository: TransferRepository) {
    suspend fun execute() = transferRepository.deleteTransferTable()
}
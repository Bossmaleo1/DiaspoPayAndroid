package com.android.diaspopay.domain.usecase.transfer

import com.android.diaspopay.data.model.api.ApiTransferResponse
import com.android.diaspopay.data.util.Resource
import com.android.diaspopay.domain.repository.TransferRepository

class GetTransferUseCase(private val transferRepository: TransferRepository) {

    suspend fun execute(sender: String, page: Int): Resource<ApiTransferResponse> {
        return  transferRepository.getTransfers(sender,page)
    }

}
package com.android.diaspopay.data.repository.dataSource.transfer

import com.android.diaspopay.data.model.api.ApiTransferResponse
import retrofit2.Response

interface TransferRemoteDataSource {
    suspend fun getTransfers(
        sender: String,
        page: Int,
        pagination: Boolean,
        token: String
    ): Response<ApiTransferResponse>
}
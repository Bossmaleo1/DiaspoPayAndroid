package com.android.diaspopay.data.repository.dataSourceImpl.transfer

import com.android.diaspopay.data.api.service.TransferAPIService
import com.android.diaspopay.data.model.api.ApiTransferResponse
import com.android.diaspopay.data.repository.dataSource.transfer.TransferRemoteDataSource
import retrofit2.Response

class TransferRemoteDataSourceImpl(
    private val transferAPIService: TransferAPIService
): TransferRemoteDataSource {

    override suspend fun getTransfers(sender: String, page: Int, pagination: Boolean, token: String): Response<ApiTransferResponse> {
        return transferAPIService.getTransfers(sender,page,pagination,token)
    }

}
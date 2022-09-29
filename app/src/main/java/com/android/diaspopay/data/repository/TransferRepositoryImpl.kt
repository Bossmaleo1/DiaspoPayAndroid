package com.android.diaspopay.data.repository

import com.android.diaspopay.data.model.api.ApiTransferResponse
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.data.repository.dataSource.transfer.TransferLocalDataSource
import com.android.diaspopay.data.repository.dataSource.transfer.TransferRemoteDataSource
import com.android.diaspopay.data.util.Resource
import com.android.diaspopay.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class TransferRepositoryImpl(
    private val transferLocalDataSource: TransferLocalDataSource,
    private val transferRemoteDataSource: TransferRemoteDataSource
): TransferRepository {

    override suspend fun getTransfers(
        sender: String,
        page: Int,
        pagination: Boolean,
        token: String
    ): Resource<ApiTransferResponse> {
        return responseToResourceTransfer(transferRemoteDataSource.getTransfers(sender,page,pagination,token))
    }

    override fun getSavedTransfer(): Flow<List<TransferRoom>> {
       return transferLocalDataSource.getSavedTransfers()
    }

    private fun responseToResourceTransfer(response: Response<ApiTransferResponse>): Resource<ApiTransferResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveTransfer(transfer: TransferRoom) {
        transferLocalDataSource.saveTransferToDB(transfer)
    }

    override suspend fun deleteTransfer(transfer: TransferRoom) {
        transferLocalDataSource.deleteTransferFromDB(transfer)
    }

    override suspend fun deleteTransferTable() {
        transferLocalDataSource.deleteTransferTable()
    }
}
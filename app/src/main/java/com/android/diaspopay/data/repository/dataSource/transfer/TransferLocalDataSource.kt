package com.android.diaspopay.data.repository.dataSource.transfer

import com.android.diaspopay.data.model.dataRoom.TransferRoom
import kotlinx.coroutines.flow.Flow

interface TransferLocalDataSource {
    suspend fun saveTransferToDB(transfer: TransferRoom)
    fun getSavedTransfers(): Flow<List<TransferRoom>>
    suspend fun deleteTransferFromDB(transfer: TransferRoom)
    suspend fun deleteTransferTable()
}
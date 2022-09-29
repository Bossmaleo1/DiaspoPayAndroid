package com.android.diaspopay.domain.repository

import com.android.diaspopay.data.model.api.ApiTransferResponse
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface TransferRepository {

    suspend fun getTransfers(sender: String, page: Int, pagination: Boolean, token: String): Resource<ApiTransferResponse>

    fun getSavedTransfer(): Flow<List<TransferRoom>>

    suspend fun saveTransfer(transfer: TransferRoom)

    suspend fun deleteTransfer(transfer: TransferRoom)

    suspend fun deleteTransferTable()
}
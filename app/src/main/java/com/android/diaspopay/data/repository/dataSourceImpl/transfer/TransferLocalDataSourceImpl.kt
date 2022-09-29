package com.android.diaspopay.data.repository.dataSourceImpl.transfer

import com.android.diaspopay.data.db.dao.TransferDAO
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.data.repository.dataSource.transfer.TransferLocalDataSource
import kotlinx.coroutines.flow.Flow

class TransferLocalDataSourceImpl(
    private val transferDAO: TransferDAO
): TransferLocalDataSource {

    override suspend fun saveTransferToDB(transfer: TransferRoom) {
        transferDAO.insert(transfer)
    }

    override fun getSavedTransfers(): Flow<List<TransferRoom>> {
        return transferDAO.getAllTransfers()
    }

    override suspend fun deleteTransferFromDB(transfer: TransferRoom) {
       transferDAO.deleteTransfer(transfer)
    }

    override suspend fun deleteTransferTable() {
        transferDAO.deleteTableTransfer()
    }
}
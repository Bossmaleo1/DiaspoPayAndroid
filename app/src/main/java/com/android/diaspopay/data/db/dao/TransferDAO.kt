package com.android.diaspopay.data.db.dao

import androidx.room.*
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface TransferDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transfer: TransferRoom)

    @Query("SELECT * FROM transfer_data_table")
    fun getAllTransfers(): Flow<List<TransferRoom>>

    @Delete()
    suspend fun deleteTransfer(transfer: TransferRoom)

    @Query("DELETE  FROM transfer_data_table")
    suspend fun deleteTableTransfer()
}
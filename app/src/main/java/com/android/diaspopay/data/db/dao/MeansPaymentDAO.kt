package com.android.diaspopay.data.db.dao

import androidx.room.*
import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface MeansPaymentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meanPayment: MeansPaymentRoom)

    @Query("SELECT * FROM mean_payment_data_table")
    fun getAllMeansPayment(): Flow<List<MeansPaymentRoom>>

    @Delete()
    suspend fun deleteMeanPayment(meanPayment: MeansPaymentRoom)

    @Query("DELETE  FROM mean_payment_data_table")
    suspend fun deleteTableMeansPayment()
}
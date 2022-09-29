package com.android.diaspopay.data.repository.dataSource.meanspayment


import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import kotlinx.coroutines.flow.Flow

interface MeansPaymentLocalDataSource {
    suspend fun saveMeansPaymentToDB(meansPayment: MeansPaymentRoom)
    fun getSavedMeansPayment(): Flow<List<MeansPaymentRoom>>
    suspend fun deleteMeansPaymentFromDB(meanspayment: MeansPaymentRoom)
    suspend fun deleteMeansPaymentTable()
}
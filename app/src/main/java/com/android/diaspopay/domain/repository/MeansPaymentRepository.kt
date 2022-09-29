package com.android.diaspopay.domain.repository

import com.android.diaspopay.data.model.api.ApiMeansPaymentResponse
import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MeansPaymentRepository {

    suspend fun getMeansPayments(): Resource<ApiMeansPaymentResponse>

    fun getSavedMeansPayment(): Flow<List<MeansPaymentRoom>>

    suspend fun saveMeansPayment(meansPayment: MeansPaymentRoom)

    suspend fun deleteMeansPayment(meansPayment: MeansPaymentRoom)

    suspend fun deleteMeansPaymentTable()

}
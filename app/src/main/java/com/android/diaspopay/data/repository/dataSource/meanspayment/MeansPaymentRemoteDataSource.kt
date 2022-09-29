package com.android.diaspopay.data.repository.dataSource.meanspayment

import com.android.diaspopay.data.model.api.ApiMeansPaymentResponse
import retrofit2.Response

interface MeansPaymentRemoteDataSource {
    suspend fun getMeansPayment(): Response<ApiMeansPaymentResponse>
}
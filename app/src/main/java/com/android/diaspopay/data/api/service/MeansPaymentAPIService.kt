package com.android.diaspopay.data.api.service

import com.android.diaspopay.data.model.api.ApiMeansPaymentResponse
import retrofit2.Response
import retrofit2.http.GET

interface MeansPaymentAPIService {
    @GET("")
    suspend fun getMeansPayments(): Response<ApiMeansPaymentResponse>
}
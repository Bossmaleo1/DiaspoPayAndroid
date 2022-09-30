package com.android.diaspopay.data.api.service

import com.android.diaspopay.data.model.api.ApiMeansPaymentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MeansPaymentAPIService {
    @GET("/api/means_payments")
    suspend fun getMeansPayments(
        @Query("user")
        user: String,
        @Query("_page")
        page: Int,
        @Query("pagination")
        pagination: Boolean,
        @Header("Authorization")
        token: String
    ): Response<ApiMeansPaymentResponse>
}
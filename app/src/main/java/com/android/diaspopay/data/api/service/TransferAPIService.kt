package com.android.diaspopay.data.api.service

import com.android.diaspopay.data.model.api.ApiTransferResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TransferAPIService {

    @GET("/api/transfers")
    suspend fun getTransfers(
        @Query("sender")
        sender: String,
        @Query("_page")
        page: Int,
        @Query("pagination")
        pagination: Boolean,
        @Header("Authorization")
        token: String
    ): Response<ApiTransferResponse>

}
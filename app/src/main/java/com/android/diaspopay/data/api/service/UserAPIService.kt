package com.android.diaspopay.data.api.service

import com.android.diaspopay.data.model.api.ApiLoginResponse
import com.android.diaspopay.data.model.api.ApiTokenResponse
import com.android.diaspopay.data.model.api.ApiUserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPIService {
    @POST("/api/login_check")
    suspend fun getToken(
        @Body apiLogin: ApiLoginResponse
    ): Response<ApiTokenResponse>

    @GET("/api/users")
    suspend fun getUser(
        @Query("username")
        userName: String,
        @Header("Authorization")
        token: String
    ): Response<ApiUserResponse>
}
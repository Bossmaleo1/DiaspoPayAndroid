package com.android.diaspopay.data.model.api

import com.google.gson.annotations.SerializedName

data class ApiTokenResponse(
    @SerializedName("token")
    val token: String
)
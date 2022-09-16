package com.android.diaspopay.data.model.api

import com.android.diaspopay.data.model.data.User
import com.google.gson.annotations.SerializedName

data class ApiUserResponse(
    @SerializedName("hydra:member")
    val Users: List<User>
)
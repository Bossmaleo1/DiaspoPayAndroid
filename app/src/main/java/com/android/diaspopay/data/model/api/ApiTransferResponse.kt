package com.android.diaspopay.data.model.api

import com.android.diaspopay.data.model.data.Transfer
import com.google.gson.annotations.SerializedName

data class ApiTransferResponse(
    @SerializedName("hydra:member")
    val transfers: List<Transfer>
)
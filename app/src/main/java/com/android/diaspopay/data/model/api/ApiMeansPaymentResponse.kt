package com.android.diaspopay.data.model.api

import com.android.diaspopay.data.model.data.MeansPayment
import com.google.gson.annotations.SerializedName

data class ApiMeansPaymentResponse(
    @SerializedName("hydra:member")
    val transfers: List<MeansPayment>
)
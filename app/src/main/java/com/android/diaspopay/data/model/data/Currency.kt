package com.android.diaspopay.data.model.data

import com.google.gson.annotations.SerializedName

data class Currency (
    @SerializedName("id")
    var id: Int?,
    var initialCurrency: String,
    var finalCurrency: String,
    var euroExChangeRate: Float,
    var androidCountryCode: String)
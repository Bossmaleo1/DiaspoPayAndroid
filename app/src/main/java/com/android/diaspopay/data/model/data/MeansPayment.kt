package com.android.diaspopay.data.model.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class MeansPayment (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("number")
    var number: String,
    @SerializedName("date")
    var date: Date,
    @SerializedName("cvv")
    var cvv: Int)
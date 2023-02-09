package com.android.diaspopay.data.model.data

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Transfer (
    @SerializedName("id")
    var id: Int?,
    @SerializedName("trackingNumber")
    var trackingNumber: String,
    @SerializedName("published")
    var published: Date,
    @SerializedName("amount")
    var amount: Float,
    @SerializedName("fee")
    var fee: Int,
    @SerializedName("discount")
    var discount: Int,
    @SerializedName("exchangeRate")
    var exchangeRate: Int,
    @SerializedName("sendingCountryIsoCode")
    var sendingCountryIsoCode: String,
    @SerializedName("transferMotif")
    var transferMotif: String,
    @SerializedName("meansPayment")
    var meansPayment: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("receiverName")
    var receiverName: String,
    @SerializedName("receiverPhoneNumber")
    var receiverPhoneNumber: String,
    @SerializedName("details")
    var details: String,
    @SerializedName("currency")
    var currency: Currency)
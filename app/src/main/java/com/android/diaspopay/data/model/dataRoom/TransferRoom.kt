package com.android.diaspopay.data.model.dataRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "transfer_data_table",
    indices = [
        Index(value = ["transfer_id"], unique = true)
    ],
)
data class TransferRoom(
    @ColumnInfo(name = "transfer_id")
    @PrimaryKey(autoGenerate = false)
    var id: Int?,
    @ColumnInfo(name = "transfer_tracking_number")
    var trackingNumber: String,
    @ColumnInfo(name = "transfer_published")
    var published: String,
    @ColumnInfo(name = "transfer_amount")
    var amount: Float,
    @ColumnInfo(name = "transfer_fee")
    var fee: Int,
    @ColumnInfo(name = "transfer_discount")
    var discount: Int,
    @ColumnInfo(name = "transfer_exchange_rate")
    var exchangeRate: Int,
    @ColumnInfo(name = "transfer_sending_country_iso_code")
    var sendingCountryIsoCode: String,
    @ColumnInfo(name = "transfer_transfer_motif")
    var transferMotif: String,
    @ColumnInfo(name = "transfer_status")
    var status: String,
    @ColumnInfo(name = "transfer_details")
    var details: String,
    @ColumnInfo(name = "transfer_beneficiary_id")
    var beneficiaryId: String,
    @ColumnInfo(name = "transfer_beneficiary_last_name")
    var beneficiaryLastName: String,
    @ColumnInfo(name = "transfer_beneficiary_first_name")
    var beneficiaryFistName: String,
    @ColumnInfo(name = "transfer_beneficiary_phone")
    var beneficiaryPhone: String,
    @ColumnInfo(name = "transfer_beneficiary_email")
    var beneficiaryEmail: String,
    @ColumnInfo(name = "transfer_beneficiary_nationality")
    var beneficiaryNationality: String,
    @ColumnInfo(name = "transfer_beneficiary_sex")
    var beneficiarySex: String,
)
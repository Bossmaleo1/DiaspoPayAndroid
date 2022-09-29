package com.android.diaspopay.data.model.dataRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "mean_payment_data_table",
    indices = [
        Index(value = ["mean_payment_id"], unique = true)
    ],
)
data class MeansPaymentRoom (
    @ColumnInfo(name = "mean_payment_id")
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    @ColumnInfo(name = "mean_payment_name")
    var name: String,
    @ColumnInfo(name = "mean_payment_number")
    var number: String,
    @ColumnInfo(name = "mean_payment_date")
    var date: String,
    @ColumnInfo(name = "mean_payment_cvv")
    var cvv: String)
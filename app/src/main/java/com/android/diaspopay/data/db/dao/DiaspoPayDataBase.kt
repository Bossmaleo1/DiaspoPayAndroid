package com.android.diaspopay.data.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.data.model.dataRoom.TokenRoom
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.data.model.dataRoom.UserRoom

@Database(
    entities = [
        UserRoom::class,
        TokenRoom::class,
        MeansPaymentRoom::class,
        TransferRoom::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DiaspoPayDataBase : RoomDatabase() {

    abstract fun getUserDAO(): UserDAO

    abstract fun getTransferDAO(): TransferDAO

    abstract  fun getMeansPaymentDAO(): MeansPaymentDAO

}
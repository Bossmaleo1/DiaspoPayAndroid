package com.android.diaspopay.data.repository.dataSourceImpl.meanspayment

import com.android.diaspopay.data.db.dao.MeansPaymentDAO
import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentLocalDataSource
import kotlinx.coroutines.flow.Flow

class MeansPaymentLocalDataSourceImpl(
    private val meansPaymentDAO: MeansPaymentDAO
): MeansPaymentLocalDataSource {

    override suspend fun saveMeansPaymentToDB(meansPayment: MeansPaymentRoom) {
        meansPaymentDAO.insert(meansPayment)
    }

    override fun getSavedMeansPayment(): Flow<List<MeansPaymentRoom>> {
        return meansPaymentDAO.getAllMeansPayment()
    }

    override suspend fun deleteMeansPaymentFromDB(meanspayment: MeansPaymentRoom) {
       meansPaymentDAO.deleteMeanPayment(meanspayment)
    }

    override suspend fun deleteMeansPaymentTable() {
        meansPaymentDAO.deleteTableMeansPayment()
    }

}
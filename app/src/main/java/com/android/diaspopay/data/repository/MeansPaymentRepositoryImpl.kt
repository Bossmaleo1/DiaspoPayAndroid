package com.android.diaspopay.data.repository

import com.android.diaspopay.data.model.api.ApiMeansPaymentResponse
import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentLocalDataSource
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentRemoteDataSource
import com.android.diaspopay.data.util.Resource
import com.android.diaspopay.domain.repository.MeansPaymentRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MeansPaymentRepositoryImpl(
    private val meansPaymentLocalDataSource: MeansPaymentLocalDataSource,
    private val meansPaymentRemoteDataSource: MeansPaymentRemoteDataSource
): MeansPaymentRepository {

    override suspend fun getMeansPayments(
        user: String,
        page: Int,
        pagination: Boolean,
        token: String
    ): Resource<ApiMeansPaymentResponse> {
        return responseToResourceMeansPayment(meansPaymentRemoteDataSource.getMeansPayment(user,page,pagination,token))
    }

    override fun getSavedMeansPayment(): Flow<List<MeansPaymentRoom>> {
        return meansPaymentLocalDataSource.getSavedMeansPayment()
    }

    private fun responseToResourceMeansPayment(response: Response<ApiMeansPaymentResponse>): Resource<ApiMeansPaymentResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveMeansPayment(meansPayment: MeansPaymentRoom) {
        meansPaymentLocalDataSource.saveMeansPaymentToDB(meansPayment)
    }

    override suspend fun deleteMeansPayment(meansPayment: MeansPaymentRoom) {
        meansPaymentLocalDataSource.deleteMeansPaymentFromDB(meansPayment)
    }

    override suspend fun deleteMeansPaymentTable() {
        meansPaymentLocalDataSource.deleteMeansPaymentTable()
    }
}
package com.android.diaspopay.data.repository.dataSourceImpl.meanspayment

import com.android.diaspopay.data.api.service.MeansPaymentAPIService
import com.android.diaspopay.data.model.api.ApiMeansPaymentResponse
import com.android.diaspopay.data.repository.dataSource.meanspayment.MeansPaymentRemoteDataSource
import retrofit2.Response

class MeansPaymentRemoteDataSourceImpl(
    private val meansPaymentAPIService: MeansPaymentAPIService
): MeansPaymentRemoteDataSource {

    override suspend fun getMeansPayment(
        user: String,
        page: Int,
        pagination: Boolean,
        token: String
    ): Response<ApiMeansPaymentResponse> {
        return meansPaymentAPIService.getMeansPayments(user,page,pagination,token)
    }
}
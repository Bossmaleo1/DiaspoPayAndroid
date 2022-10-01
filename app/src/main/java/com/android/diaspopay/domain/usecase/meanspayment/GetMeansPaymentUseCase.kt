package com.android.diaspopay.domain.usecase.meanspayment

import com.android.diaspopay.data.model.api.ApiMeansPaymentResponse
import com.android.diaspopay.data.model.api.ApiTransferResponse
import com.android.diaspopay.data.util.Resource
import com.android.diaspopay.domain.repository.MeansPaymentRepository

class GetMeansPaymentUseCase(private val  meansPaymentRepository: MeansPaymentRepository) {
    suspend fun execute(user: String, page: Int, pagination: Boolean, token: String): Resource<ApiMeansPaymentResponse> {
        return meansPaymentRepository.getMeansPayments(user,page,pagination, token)
    }
}
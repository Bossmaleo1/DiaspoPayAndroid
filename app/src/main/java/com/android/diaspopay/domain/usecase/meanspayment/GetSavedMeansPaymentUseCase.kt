package com.android.diaspopay.domain.usecase.meanspayment

import com.android.diaspopay.data.model.data.MeansPayment
import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.domain.repository.MeansPaymentRepository
import kotlinx.coroutines.flow.Flow

class GetSavedMeansPaymentUseCase(private val  meansPaymentRepository: MeansPaymentRepository) {
    fun execute(): Flow<List<MeansPaymentRoom>> {
        return meansPaymentRepository.getSavedMeansPayment()
    }
}
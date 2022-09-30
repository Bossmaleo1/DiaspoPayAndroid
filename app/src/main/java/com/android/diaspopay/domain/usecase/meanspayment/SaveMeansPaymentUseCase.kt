package com.android.diaspopay.domain.usecase.meanspayment

import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.domain.repository.MeansPaymentRepository

class SaveMeansPaymentUseCase(private val  meansPaymentRepository: MeansPaymentRepository) {
    suspend fun execute(meansPayment: MeansPaymentRoom) = meansPaymentRepository.saveMeansPayment(meansPayment)
}
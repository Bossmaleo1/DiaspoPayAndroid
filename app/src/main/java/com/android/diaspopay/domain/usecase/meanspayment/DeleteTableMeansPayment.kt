package com.android.diaspopay.domain.usecase.meanspayment

import com.android.diaspopay.domain.repository.MeansPaymentRepository

class DeleteTableMeansPayment(private val  meansPaymentRepository: MeansPaymentRepository) {
    suspend fun execute() = meansPaymentRepository.deleteMeansPaymentTable()
}
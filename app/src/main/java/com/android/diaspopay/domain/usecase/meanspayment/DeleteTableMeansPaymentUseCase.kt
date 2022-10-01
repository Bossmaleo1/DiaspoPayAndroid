package com.android.diaspopay.domain.usecase.meanspayment

import com.android.diaspopay.domain.repository.MeansPaymentRepository

class DeleteTableMeansPaymentUseCase(private val  meansPaymentRepository: MeansPaymentRepository) {
    suspend fun execute() = meansPaymentRepository.deleteMeansPaymentTable()
}
package com.android.diaspopay.presentation.viewModel.meansPayment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.android.diaspopay.data.model.data.MeansPayment
import com.android.diaspopay.data.model.data.Transfer
import com.android.diaspopay.data.model.dataRoom.MeansPaymentRoom
import com.android.diaspopay.domain.usecase.meanspayment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MeansPaymentViewModel(
    private val app: Application,
    private val getMeansPaymentUseCase: GetMeansPaymentUseCase,
    private val getSavedMeansPaymentUseCase: GetSavedMeansPaymentUseCase,
    private val getSearchMeansPaymentUseCase: GetSearchMeansPaymentUseCase,
    private val savedMeansPaymentUseCase: SaveMeansPaymentUseCase,
    private val updateSavedMeansPaymentUseCase: UpdateSavedMeansPaymentUseCase,
    private val deleteSavedMeansPaymentUseCase: DeleteSavedMeansPaymentUseCase,
    private val deleteTableMeansPayment: DeleteTableMeansPayment
): AndroidViewModel(app) {

    val meansPaymentStateRemoteList = mutableStateListOf<Transfer>()
    private val meansPaymentList: MutableLiveData<List<Transfer>> = MutableLiveData()
    val currentPage : MutableState<Int> = mutableStateOf(1)

    fun getMeansPayment(user: String, page: Int, pagination: Boolean,token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = getMeansPaymentUseCase.execute(user,page,pagination,token)
                } else {
                    Toast.makeText(app.applicationContext,"Internet is not available", Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception) {
                Toast.makeText(app,e.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getAllMeansPayments() = liveData {
        getSavedMeansPaymentUseCase.execute().collect {
            emit(it)
        }
    }

    fun saveMeansPaymentRoom(meansPayment: MeansPayment) = viewModelScope.launch {
        savedMeansPaymentUseCase.execute(
            MeansPaymentRoom(
                meansPayment.id,
                meansPayment.name,
                meansPayment.number,
                meansPayment.date.toString(),
                meansPayment.cvv.toString()
            )
        )
    }

    fun initMeansPayment() {
        meansPaymentStateRemoteList.removeAll(meansPaymentStateRemoteList)
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}
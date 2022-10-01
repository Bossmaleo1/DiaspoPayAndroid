package com.android.diaspopay.presentation.viewModel.transfer

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
import com.android.diaspopay.data.model.data.Transfer
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.domain.usecase.transfer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val app: Application,
    private val getTransferUseCase: GetTransferUseCase,
    private val getSavedTransferUseCase: GetSavedTransferUseCase,
    private val saveTransferUseCase: SaveTransferUseCase,
    private val updateSavedTransferUseCase: UpdateSavedTransferUseCase,
    private val deleteSavedTransferUseCase: DeleteSavedTransferUseCase
    ): AndroidViewModel(app) {

     val transferStateRemoteList = mutableStateListOf<Transfer>()
    private val transferList: MutableLiveData<List<Transfer>> = MutableLiveData()
     val currentPage : MutableState<Int> = mutableStateOf(1)

    fun getTransfer(sender: String,page: Int,pagination: Boolean,token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = getTransferUseCase.execute(sender, page,pagination = true, token = "Bearer $token")
                    apiResult.data?.let {
                        transferList.postValue(it.transfers)
                        transferStateRemoteList.addAll(it.transfers)
                        currentPage.value = page
                    }
                } else {
                    Toast.makeText(app.applicationContext,"Internet is not available", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(app,e.message.toString(),Toast.LENGTH_LONG).show()
            }

        }
    }

    fun saveTransferRoom(transfer: Transfer) = viewModelScope.launch {
        saveTransferUseCase.execute(
            TransferRoom(
                transfer.id,
                transfer.trackingNumber,
                transfer.published.toString(),
                transfer.amount,
                transfer.fee,
                transfer.discount,
                transfer.exchangeRate,
                transfer.sendingCountryIsoCode,
                transfer.transferMotif,
                transfer.beneficiary,
                transfer.sender,
                transfer.status,
                transfer.details
            )
        )
    }

    fun getAllTransfer() = liveData {
        getSavedTransferUseCase.execute().collect {
            emit(it)
        }
    }

    fun initTransfer() {
        transferStateRemoteList.removeAll(transferStateRemoteList)
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
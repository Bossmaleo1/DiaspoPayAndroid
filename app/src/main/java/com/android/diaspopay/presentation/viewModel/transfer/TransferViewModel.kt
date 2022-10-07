package com.android.diaspopay.presentation.viewModel.transfer

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.android.diaspopay.data.model.data.Transfer
import com.android.diaspopay.data.model.dataRoom.TokenRoom
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.domain.usecase.transfer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
     val serverError: MutableState<Boolean> = mutableStateOf(true)

    private val networkStateMutable: MutableLiveData<Boolean> = MutableLiveData()
    val networkStateValue: LiveData<Boolean> = networkStateMutable

    private val isEmptyResultMutable: MutableLiveData<Boolean> = MutableLiveData()
    val isEmptyResultValue: LiveData<Boolean> = isEmptyResultMutable

    private val isProgressBarMutable: MutableLiveData<Boolean> = MutableLiveData()
    val isProgressBarValue: LiveData<Boolean> = isProgressBarMutable

    fun getTransfer(sender: String,page: Int,pagination: Boolean,token: String) {
        serverError.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (isNetworkAvailable(app)) {
                networkStateMutable.postValue(true)
                isProgressBarMutable.postValue(true)
                try {
                        val apiResult = getTransferUseCase.execute(sender, page,pagination = true, token = "Bearer $token")
                        apiResult.data?.let {
                            transferList.postValue(it.transfers)
                            transferStateRemoteList.addAll(it.transfers)
                            currentPage.value = page

                            if (currentPage.value == 1 && it.transfers.isEmpty()) {
                                isEmptyResultMutable.postValue(false)
                            }

                            if (currentPage.value > 1 && it.transfers.isEmpty()) {
                                isProgressBarMutable.postValue(false)
                            }

                            if (it.transfers.size == 10) {
                                isProgressBarMutable.postValue(true)
                            }

                            if (currentPage.value == 1 && transferStateRemoteList.isNotEmpty()) {
                                isEmptyResultMutable.postValue(true)
                            }
                        }


                        serverError.value = true
                } catch (e: Exception) {
                    serverError.value = false
                    isProgressBarMutable.postValue(false)
                }
            } else {
                networkStateMutable.postValue(false)
                isProgressBarMutable.postValue(false)
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
                transfer.beneficiary.id.toString(),
                transfer.sender.id.toString(),
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
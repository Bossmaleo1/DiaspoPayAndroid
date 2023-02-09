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
import com.android.diaspopay.data.model.data.Currency
import com.android.diaspopay.data.model.data.Transfer
import com.android.diaspopay.data.model.dataRoom.TransferRoom
import com.android.diaspopay.domain.usecase.transfer.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
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

    private val isOffLineMutable: MutableLiveData<Boolean> = MutableLiveData()
    val isOffLineValue: LiveData<Boolean> = isOffLineMutable

    fun getTransfer(sender: String,page: Int,pagination: Boolean,token: String) {
        serverError.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (isNetworkAvailable(app)) {
                networkStateMutable.postValue(true)
                isProgressBarMutable.postValue(true)
                isOffLineMutable.postValue(false)
                try {
                        val apiResult = getTransferUseCase.execute(sender, page,pagination = true, token = "Bearer $token")
                        apiResult.data?.let {
                            transferList.postValue(it.transfers)
                            transferStateRemoteList.addAll(it.transfers)
                            currentPage.value = page
                            if (currentPage.value <= 5) {
                                //saveAllTransfer(it.transfers)
                            }
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
                        //We generate server code issues
                        if (apiResult.data == null) {
                            serverError.value = false
                            isProgressBarMutable.postValue(false)
                        }
                } catch (e: Exception) {
                    serverError.value = false
                    isProgressBarMutable.postValue(false)
                }
            } else {
                networkStateMutable.postValue(false)
                isProgressBarMutable.postValue(false)
                isOffLineMutable.postValue(true)
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
                    transfer.status,
                    transfer.details,
                    transfer.receiverName,
                    transfer.receiverPhoneNumber,
                    transfer.currency.initialCurrency,
                    transfer.currency.finalCurrency,
                    transfer.currency.androidCountryCode,
                    transfer.currency.euroExChangeRate
                )
            )
    }

    fun saveAllTransfer(transfers: List<Transfer>) = viewModelScope.launch {
        transfers.forEach { transfer ->
            saveTransferRoom(transfer)
        }
    }

    fun getAllTransfer() = liveData {
        getSavedTransferUseCase.execute().collect {transfers ->
            Log.d("MALEO9393"," Our Database 1")
            emit(transfers)
            transfers.forEach { transfersRoom ->
                try {
                    val dateFormat = SimpleDateFormat("E MMM dd hh:mm:ss 'GMT'Z yyyy", Locale.getDefault())
                    val date: Date = dateFormat.parse(transfersRoom.published) as Date

                    val currency = Currency(
                        transfersRoom.id,
                        transfersRoom.initialCurrency,
                        transfersRoom.finalCurrency,
                        transfersRoom.euroExChangeRate,
                        transfersRoom.androidCountryCode
                    )

                    transferStateRemoteList.add(
                        Transfer(
                            transfersRoom.id,
                            transfersRoom.trackingNumber,
                            date,
                            transfersRoom.amount,
                            transfersRoom.fee,
                            transfersRoom.discount,
                            transfersRoom.exchangeRate,
                            transfersRoom.sendingCountryIsoCode,
                            transfersRoom.transferMotif,
                            "",
                            transfersRoom.status,
                            transfersRoom.receiverName,
                            transfersRoom.receiverPhoneNumber,
                            transfersRoom.details,
                            currency
                        )
                    )
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
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
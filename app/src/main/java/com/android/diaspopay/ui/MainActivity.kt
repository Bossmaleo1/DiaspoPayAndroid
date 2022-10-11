package com.android.diaspopay.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.diaspopay.presentation.viewModel.drop.DropViewModel
import com.android.diaspopay.presentation.viewModel.drop.DropViewModelFactory
import com.android.diaspopay.presentation.viewModel.meansPayment.MeansPaymentViewModel
import com.android.diaspopay.presentation.viewModel.meansPayment.MeansPaymentViewModelFactory
import com.android.diaspopay.presentation.viewModel.transfer.TransferViewModel
import com.android.diaspopay.presentation.viewModel.transfer.TransferViewModelFactory
import com.android.diaspopay.presentation.viewModel.user.UserViewModel
import com.android.diaspopay.presentation.viewModel.user.UserViewModelFactory
import com.android.diaspopay.ui.theme.DiaspoPayTheme
import com.android.diaspopay.ui.views.HomeApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.android.diaspopay.ui.views.LaunchView
import com.android.diaspopay.ui.views.Login
import com.android.diaspopay.ui.views.NewTransfer
import com.android.diaspopay.ui.views.bottomnavigationviews.ContactsSearchView
import com.android.diaspopay.ui.views.model.Route
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userFactory: UserViewModelFactory
    @Inject
    lateinit var dropFactory: DropViewModelFactory
    @Inject
    lateinit var meansPaymentFactory: MeansPaymentViewModelFactory
    @Inject
    lateinit var transferFactory: TransferViewModelFactory

    private lateinit var meansPaymentViewModel: MeansPaymentViewModel
    private lateinit var transferViewModel: TransferViewModel
    private lateinit var userViewModel: UserViewModel //we call our login viewModel
    private lateinit var dropViewModel: DropViewModel
    var token: String? = null

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaspoPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    MainView(navController, this)
                    userViewModel.getSavedToken().observe(this as LifecycleOwner) { token ->
                        this.token = token?.token
                    }

                    CoroutineScope(Dispatchers.Main).launch {
                        delay(200)
                        if (token === null) {
                            navController.navigate(Route.loginView)
                        } else {
                           navController.navigate(Route.homeView)
                        }
                    }
                }
            }

        }
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this, userFactory)[UserViewModel::class.java]
        dropViewModel = ViewModelProvider(this, dropFactory)[DropViewModel::class.java]
        transferViewModel = ViewModelProvider(this,transferFactory)[TransferViewModel::class.java]
        meansPaymentViewModel = ViewModelProvider(this,meansPaymentFactory)[MeansPaymentViewModel::class.java]
    }

    @Composable
    @ExperimentalMaterial3Api
    fun MainView(navController: NavHostController, context: Any) {
        //We call our init view model method
        this.initViewModel()
        //This LiveData help us to change our bottom navigation view
        NavHost(navController = navController, startDestination = "launch_view" ) {
            composable(route = Route.launchView) {
                LaunchView()
            }

            composable(route = Route.loginView) {
                Login(navController,userViewModel, context)
            }

            composable(route = Route.homeView) {
                RequesReadContactPermission()
                HomeApp(
                    navController,
                    userViewModel,
                    dropViewModel,
                    meansPaymentViewModel,
                    transferViewModel
                )
            }

            composable(route = Route.createTransferView) {
                NewTransfer(navController)
            }

            composable(route = Route.searchView) {
                ContactsSearchView()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequesReadContactPermission() {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.READ_CONTACTS
        )
    )

    if (locationPermissionsState.allPermissionsGranted) {
        //Text("Thanks! I can access your exact location :D")
    } else {
        CoroutineScope(Dispatchers.Main).launch {
            locationPermissionsState.launchMultiplePermissionRequest()
        }
    }
}
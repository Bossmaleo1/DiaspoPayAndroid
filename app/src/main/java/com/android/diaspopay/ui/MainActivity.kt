package com.android.diaspopay.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.diaspopay.ui.theme.DiaspoPayTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.android.diaspopay.ui.views.LaunchView
import com.android.diaspopay.ui.views.Login
import com.android.diaspopay.ui.views.model.Route

@ExperimentalMaterial3Api
//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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

                    CoroutineScope(Dispatchers.Main).launch {
                        delay(100)
                        navController.navigate("login_view")
                    }
                }
            }

        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun MainView(navController: NavHostController, context: Any) {
    NavHost(navController = navController, startDestination = "launch_view" ) {
        composable(route = Route.launchView) {
            LaunchView()
        }

        composable(route = Route.loginView) {
            Login(navController,/*userViewModel,*/ context)
        }

       /* composable(route = WazzabyDrawerDestinations.HOME) {
            HomeApp(navController,scope, drawerState, context,  userViewModel)
        }

        composable(route = WazzabyDrawerDestinations.INSCRIPTION_FIRST) {
            FormStepFirstView(navController)
        }

        composable(route = WazzabyDrawerDestinations.INSCRIPTION_SECOND) {
            FormStepSecondView(navController)
        }

        composable(route = WazzabyDrawerDestinations.INSCRIPTION_DONE) {
            FormStepDoneView(navController)
        }*/
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiaspoPayTheme {
       // Greeting("Android")
    }
}
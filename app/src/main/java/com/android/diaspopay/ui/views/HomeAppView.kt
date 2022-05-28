package com.android.diaspopay.ui.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.bottomnavigationviews.HistoryView
import com.android.diaspopay.ui.views.model.BottomNavigationItem
import com.android.diaspopay.ui.views.model.Route
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterial3Api
fun DrawerAppBar(scope: CoroutineScope, drawerState: DrawerState, title: String, viewItem: MutableLiveData<String>, context: Any) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val listState = rememberLazyListState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                navigationIcon = {

                },
                actions = {

                    var expanded by remember { mutableStateOf(false) }
                    IconButton(onClick = { /* doSomething() */ }) {
                        BadgedBox(badge = { Badge { Text("8") } }) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                    IconButton(onClick = {
                        expanded = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Localized description"
                        )
                    }

                    //we create our Dropdown Menu Item
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("xxxx") },
                            onClick = { /* Handle edit! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.AccountCircle,
                                    contentDescription = null
                                )
                            })
                        DropdownMenuItem(
                            text = { Text("xxxx") },
                            onClick = { /* Handle settings! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.History,
                                    contentDescription = null
                                )
                            })
                    }
                },
                scrollBehavior = scrollBehavior,
                title = { Text(title) }
            )
        }) { innerPadding ->

        var saveValue by remember { mutableStateOf("") }
        viewItem.observe(context as LifecycleOwner) {
            saveValue = it;
        }

        when(saveValue) {
            Route.historyTabView ->
                LazyColumn(contentPadding = innerPadding, state = listState) {
                    items(count = 2000) {
                        HistoryView()
                    }

                    /*items(count = 3) {
                        repeat(3) {
                            PublicMessageShimmer()
                        }
                    }*/
                }
            Route.homeTabView ->
                LazyColumn(contentPadding = innerPadding, state = listState) {
                    items(count = 2000) {
                       // PrivateMessageView()
                    }
                }
        }

    }


}

@Composable
@ExperimentalMaterial3Api
fun HomeApp(navController: NavHostController, scope: CoroutineScope, drawerState: DrawerState,viewItem: MutableLiveData<String>, context: Any/*, userViewModel: UserViewModel*/) {
    val navController2 = rememberNavController()
    val navBackStackEntry by navController2.currentBackStackEntryAsState()
    /*val currentRoute =
        navBackStackEntry?.destination?.route ?: WazzabyDrawerDestinations.HOME_ROUTE*/
    var switch by rememberSaveable { mutableStateOf(true) }
    var selectedItem by remember { mutableStateOf(0) }
    viewItem.value = Route.historyTabView

    val items = listOf(
        BottomNavigationItem(
            R.drawable.baseline_history_24,
            "Historique",
            Route.historyTabView
        ),
        BottomNavigationItem(
            R.drawable.baseline_home_24,
            "Accueil",
            Route.homeTabView
        )
    )
    Scaffold(topBar = {
        DrawerAppBar(scope, drawerState, "DiaspoPay",viewItem, context)
    },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.id),
                                contentDescription = null
                            )
                        },
                        label = { Text(item.title) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            switch = index != 1
                            if (switch) {
                                viewItem.value = Route.historyTabView
                            } else {
                                viewItem.value = Route.homeTabView
                            }
                        }
                    )
                }
            }
        }, floatingActionButton = {
            if (switch) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.EuroSymbol, "") },
                    text = {
                        Text(text = "Envoyer de l'argent",
                            style = MaterialTheme.typography.titleSmall)
                    },
                    onClick = {/*do something*/ },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                )
            }
        }) {innerPadding ->


    }

}
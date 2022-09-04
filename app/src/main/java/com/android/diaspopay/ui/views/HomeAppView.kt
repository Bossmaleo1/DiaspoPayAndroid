package com.android.diaspopay.ui.views

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.bottomnavigationviews.HistoryView
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterial3Api
fun HomeApp(
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    context: Any
) {
    val navController2 = rememberNavController()
    val navBackStackEntry by navController2.currentBackStackEntryAsState()
    //val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val listState = rememberLazyListState()
    var fabExtended by rememberSaveable { mutableStateOf(true) }

    val density = LocalDensity.current
    /*val items = listOf(
        BottomNavigationItem(
            Icons.Outlined.History,
            stringResource(R.string.history),
            Route.historyTabView
        ),
        BottomNavigationItem(
            Icons.Outlined.Home,
            stringResource(R.string.home_app),
            Route.homeTabView
        )
    )
    Scaffold(topBar = {
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
                                    imageVector = Icons.Outlined.Notifications,
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
                    title = { Text(stringResource(R.string.app_name)) }
                )
            }) { innerPadding ->

            if (switch) {
                LazyColumn(contentPadding = innerPadding, state = listState) {
                    items(count = 2000) {
                        HistoryView()
                    }
                }
            } else {
                ContactsSearchView()
            }

        }
    },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.id,
                                contentDescription = null
                            )
                        },
                        label = { Text(item.title) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            switch = index != 1
                        }
                    )
                }
            }
        }, floatingActionButton = {
            if (switch) {
                //This function help us to make our button extensible
                LaunchedEffect(listState) {
                    var prev = 0
                    snapshotFlow { listState.firstVisibleItemIndex }
                        .collect {
                            fabExtended = it <= prev
                            prev = it
                        }
                }

                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.EuroSymbol, "") },
                    expanded = fabExtended,
                    text = {
                        Text(
                            text = stringResource(R.string.send_money),
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    onClick = { },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                )
            }
        }, content = { innerPadding ->


        })*/

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(topBar = {
        AnimatedVisibility(
            visible = fabExtended,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            SmallTopAppBar(
                navigationIcon = {
                    Text(text = stringResource(R.string.history), modifier = Modifier.padding(10.dp))
                },
                actions = {
                    var expanded by remember { mutableStateOf(false) }

                    IconButton(onClick = {
                        expanded = true
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "Localized description"
                        )
                    }

                    //we create our Dropdown Menu Item
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Comptes",
                                    maxLines = 1
                                )
                            },
                            onClick = { /* Handle edit! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.AccountCircle,
                                    contentDescription = null
                                )
                            })

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Paramètres",
                                    maxLines = 1
                                )
                            },
                            onClick = { /* Handle edit! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Settings,
                                    contentDescription = null
                                )
                            })

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Nous contacter",
                                    maxLines = 1
                                )
                            },
                            onClick = { /* Handle edit! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.ContactPage,
                                    contentDescription = null
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Inviter vos amis",
                                    maxLines = 1
                                )
                            },
                            onClick = { /* Handle edit! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.PeopleOutline,
                                    contentDescription = null
                                )
                            })

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "A propos",
                                    maxLines = 1
                                )
                            },
                            onClick = { /* Handle edit! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.HelpOutline,
                                    contentDescription = null
                                )
                            })
                    }

                },
                scrollBehavior = scrollBehavior,
                title = {}
            )
        }
    },
    floatingActionButton = {
        //This function help us to make our button extensible
        LaunchedEffect(listState) {
            var prev = 0
            snapshotFlow { listState.firstVisibleItemIndex }
                .collect {
                    fabExtended = it <= prev
                    prev = it
                }
        }

        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Filled.EuroSymbol, "") },
            expanded = fabExtended,
            modifier = Modifier.padding(bottom = 50.dp),
            text = {
                Text(
                    text = stringResource(R.string.send_money),
                    style = MaterialTheme.typography.titleSmall
                )
            },
            onClick = { },
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
        )

    }) { innerPadding ->
        LazyColumn(contentPadding = innerPadding, state = listState) {
            items(count = 2000) {
                HistoryView()
            }
        }
    }

}
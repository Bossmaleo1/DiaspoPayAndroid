package com.android.diaspopay.ui.views

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.bottomnavigationviews.HistoryView
import com.android.diaspopay.ui.views.model.Route

@Composable
@ExperimentalMaterial3Api
fun HomeApp(navController: NavHostController) {
    val listState = rememberLazyListState()
    var fabExtended by rememberSaveable { mutableStateOf(true) }
    val density = LocalDensity.current

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
            /* Handle settings! */
            /* Handle edit! *//* Handle edit! *//* Handle edit! *//* Handle edit! *//* Handle edit! *///we create our Dropdown Menu Item
            TopAppBar(title = {},
                navigationIcon = {
                    Row(modifier = Modifier.padding(10.dp)) {
                        Icon(
                            Icons.Outlined.History,
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(R.string.history),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                },
                actions =  {
                    var expanded by remember { mutableStateOf(false) }

                    IconButton(onClick = { /* doSomething() */ }) {
                        //We add our badges
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
                                    text = stringResource(id = R.string.account),
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
                                    text = stringResource(id = R.string.settings),
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
                                    text = stringResource(id = R.string.contact_us),
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
                                    text = stringResource(id = R.string.guest_friend),
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
                                    text = stringResource(id = R.string.about),
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

                        DropdownMenuItem(
                            text = {
                                Text(
                                    stringResource(id = R.string.logout)
                                )
                            },
                            onClick = {
                                /* Handle settings! */

                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Logout,
                                    contentDescription = null
                                )
                            })
                    }
                }, scrollBehavior = scrollBehavior
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
                modifier = Modifier.padding(bottom = 20.dp),
                text = {
                    Text(
                        text = stringResource(R.string.send_money),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                onClick = {
                    navController.navigate(Route.searchView)
                },
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
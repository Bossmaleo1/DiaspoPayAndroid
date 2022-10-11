package com.android.diaspopay.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.contact.ContactItem
import com.android.diaspopay.ui.views.model.Route
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransfer(
    navController: NavHostController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())


    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(R.string.transfer),
                modifier = Modifier.padding(start = 10.dp)
            )
        },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                } ) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions =  { }, scrollBehavior = scrollBehavior
        )
    }){innerPadding ->

    }
}
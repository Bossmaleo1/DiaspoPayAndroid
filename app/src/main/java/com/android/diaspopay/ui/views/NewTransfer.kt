package com.android.diaspopay.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.OutlinedFlag
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
    var transfer by rememberSaveable { mutableStateOf("Country") }

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
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            OutlinedTextField(
                value = "Congo Brazzaville",
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                ),
                onValueChange = { transfer = it },
                label = { Text(stringResource(id = R.string.your_email)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = { Text("") },
                leadingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.OutlinedFlag,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 0.dp, start = 30.dp, end = 30.dp),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}
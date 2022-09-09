package com.android.diaspopay.ui.views.bottomnavigationviews

import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.contact.ContactItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsSearchView() {
    val listState = rememberLazyListState()
    var searchContact by rememberSaveable { mutableStateOf("") }

    read()
    Scaffold(topBar = {
        Column (
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            OutlinedTextField(
                value = searchContact,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                ),
                onValueChange = { searchContact = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = { Text(text= stringResource(id = R.string.search), fontSize = 12.sp) },
                leadingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 30.dp, end = 30.dp)
                    .height(45.dp),
                shape = RoundedCornerShape(22.dp)
            )
        }
    }){innerPadding ->
        LazyColumn(contentPadding = innerPadding, state = listState) {
            items(count = 2000) {
                ContactItem()
            }
        }
    }
}

@Composable
fun read() {
    val context = LocalContext.current
    var cols = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()

    var rs = context.applicationContext.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                                cols,null,null,
                                                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
    /*Log.d("MALEOTest9393Regis", "Test MALEO-Sama !!")
    if (rs?.moveToNext()!!) {
        Log.d("MALEOTest9393Regis","Phone Number : " + rs.getString(1))
        Log.d("MALEOTest9393Regis", "User Name : " + rs.getString(0))
        Toast.makeText(context.applicationContext,rs.getString(1),Toast.LENGTH_LONG).show()
    }*/
//contentResolver.query
}


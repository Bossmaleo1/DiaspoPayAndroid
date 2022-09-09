package com.android.diaspopay.ui.views.bottomnavigationviews

import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.contact.ContactItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsSearchView() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val listState = rememberLazyListState()

    read()
    Scaffold(topBar = {
        SmallTopAppBar(
            navigationIcon = {
                Row(modifier = Modifier.padding(10.dp)) {
                    Icon(
                        Icons.Outlined.ContactPage,
                        contentDescription = null
                    )
                    Text(
                        text = "Contacts",
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            },
            actions = {},
            scrollBehavior = scrollBehavior,
            title = {}
        )
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


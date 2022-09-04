package com.android.diaspopay.ui.views.bottomnavigationviews

import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ContactsSearchView() {
    read()
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
    Log.d("MALEOTest9393Regis", "Test MALEO-Sama !!")
    if (rs?.moveToNext()!!) {
        Log.d("MALEOTest9393Regis","Phone Number : " + rs.getString(1))
        Log.d("MALEOTest9393Regis", "User Name : " + rs.getString(0))
        Toast.makeText(context.applicationContext,rs.getString(1),Toast.LENGTH_LONG).show()
    }
//contentResolver.query
}


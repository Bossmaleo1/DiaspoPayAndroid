package com.android.diaspopay.ui.views.bottomnavigationviews

import android.icu.text.NumberFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.diaspopay.R
import com.android.diaspopay.data.model.data.Transfer
import com.android.diaspopay.ui.views.utils.getColorStatus
import com.android.diaspopay.ui.views.utils.getStatus
import java.text.SimpleDateFormat
import java.util.*

const val IN_PROGRESS = "IN_PROGRESS"
const val DONE = "DONE"
const val CANCEL = "Canceled"

@ExperimentalMaterial3Api
@Composable
fun HistoryView(transfer: Transfer) {
    val userName by rememberSaveable { mutableStateOf("${transfer?.beneficiary?.firstName} ${transfer?.beneficiary?.lastName}") }
    val amount by rememberSaveable { mutableStateOf("${transfer.amount} €") }

    val formatter: SimpleDateFormat = SimpleDateFormat("EEE d MMM yy", Locale.getDefault())
    val published = formatter.format(transfer.published)

    //val currency = Currency()


    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_account_circle_black_48),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .height(50.dp)
                        .width(50.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(25.dp)))
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(
                            text = userName,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = amount,
                            modifier = Modifier.padding(top = 20.dp),
                            style = MaterialTheme.typography.titleSmall
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .clickable { },
                        ) {
                            Icon(
                                Icons.Outlined.MoreHoriz,
                                contentDescription = null
                            )

                            Text(
                                text = stringResource(R.string.details),
                                modifier = Modifier.padding(start = 4.dp))
                        }
                    }

                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(
                            text = published,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text =  getStatus(transfer.status),
                            modifier = Modifier.padding(top = 20.dp),
                            style = MaterialTheme.typography.titleSmall,
                            color = getColorStatus(transfer.status)
                        )

                        Row(modifier = Modifier
                            .padding(top = 20.dp)
                            .clickable { }) {
                            Icon(
                                Icons.Outlined.Refresh,
                                contentDescription = null
                            )

                            Text(text = stringResource(R.string.resend), modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }
            }
        }

        Divider(
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(0.20.dp),
        )

    }
}
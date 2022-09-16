package com.android.diaspopay.ui.views.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.diaspopay.R
import com.android.diaspopay.data.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactItem(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent),
        onClick = {}
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
                            text = contact.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = contact.phoneNumber,
                            modifier = Modifier.padding(4.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
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
package com.android.diaspopay.ui.views.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.PanTool
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.diaspopay.R

@Composable
fun emptyResult(paddingValues: PaddingValues) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Outlined.PanTool,
                contentDescription = null,
                tint =  MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxSize(0.3F)
            )

            Text(
                text = stringResource(R.string.empty_result),
                color =  MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 4.dp, top = 10.dp))
        }
    }
}
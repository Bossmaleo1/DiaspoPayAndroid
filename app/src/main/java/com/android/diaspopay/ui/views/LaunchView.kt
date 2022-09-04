package com.android.diaspopay.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.diaspopay.R
import kotlinx.coroutines.delay

@Composable
fun LaunchView() {
    var visibleImage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            AnimatedVisibility(
                visibleImage,
                enter = slideIn(tween(100, easing = LinearOutSlowInEasing)) { fullSize ->
                    // Specifies the starting offset of the slide-in to be 1/4 of the width to the right,
                    // 100 (pixels) below the content position, which results in a simultaneous slide up
                    // and slide left.
                    IntOffset(fullSize.width / 4, 100)
                },
                exit = slideOut(tween(100, easing = FastOutSlowInEasing)) {
                    // The offset can be entirely independent of the size of the content. This specifies
                    // a target offset 180 pixels to the left of the content, and 50 pixels below. This will
                    // produce a slide-left combined with a slide-down.
                    IntOffset(-180, 50)
                },
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 40.dp, start = 0.dp, end = 0.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 40.sp
                )
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "©DiaspoPay 2022 V. 1.0",
            color = MaterialTheme.colorScheme.primary)
    }

    LaunchedEffect(true) {
        delay(6)
        visibleImage = true
    }
}
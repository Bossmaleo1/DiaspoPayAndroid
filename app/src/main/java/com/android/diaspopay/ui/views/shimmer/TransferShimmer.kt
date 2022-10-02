package com.android.diaspopay.ui.views.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.utils.getColorStatus
import com.android.diaspopay.ui.views.utils.getStatus

@ExperimentalMaterial3Api
@Composable
fun TransferShimmer() {

    //These colors will be used on the brush. The lightest color should be in the middle

    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
    ShimmerGridItem(brush = brush)
    ShimmerGridItem(brush = brush)
    ShimmerGridItem(brush = brush)
    ShimmerGridItem(brush = brush)
    ShimmerGridItem(brush = brush)
    ShimmerGridItem(brush = brush)
    ShimmerGridItem(brush = brush)
    ShimmerGridItem(brush = brush)
}

@ExperimentalMaterial3Api
@Composable
fun ShimmerGridItem(brush: Brush) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(
                modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(brush))

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 10.dp), verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.padding(4.dp)) {
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.5f)
                            .background(brush)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.5f)
                            .background(brush)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.5f)
                            .background(brush)
                    )
                }

                Column(modifier = Modifier.padding(4.dp)) {
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.5f)
                            .background(brush)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.5f)
                            .background(brush)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.5f)
                            .background(brush)
                    )
                }
            }
        }
    }
}
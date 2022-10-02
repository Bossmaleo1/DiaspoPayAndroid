package com.android.diaspopay.ui.views.utils

import android.icu.text.NumberFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.android.diaspopay.R
import com.android.diaspopay.ui.views.bottomnavigationviews.CANCEL
import com.android.diaspopay.ui.views.bottomnavigationviews.DONE
import com.android.diaspopay.ui.views.bottomnavigationviews.IN_PROGRESS


fun getAmountFormat(amount: Float): String {
    return  NumberFormat.getInstance().format(amount)
}

@Composable
fun getStatus(status: String): String {
    when (status) {
        DONE -> {
            return stringResource(R.string.paid)
        }
        IN_PROGRESS -> {
            return stringResource(R.string.in_progress)
        }
        CANCEL ->  {
            return stringResource(R.string.cancel)
        }
        else -> {
            return "Error"
        }
    }
}

@Composable
fun getColorStatus(status: String): Color {
    when (status) {
        DONE -> {
            return Color.Green
        }
        IN_PROGRESS -> {
            return  colorResource(R.color.orange)
        }
        CANCEL ->  {
            return Color.Red
        }
        else -> {
            return Color.Green
        }
    }
}


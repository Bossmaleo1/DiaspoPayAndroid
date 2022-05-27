package com.android.diaspopay

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DiaspoPayApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        //DynamicColors.applyToActivitiesIfAvailable
        DynamicColors.applyToActivitiesIfAvailable(this, R.style.AppTheme_Overlay)
    }
}
package com.seongju.androidtestingproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidTestingApp: Application() {

    override fun onCreate() {
        super.onCreate()

    }

}
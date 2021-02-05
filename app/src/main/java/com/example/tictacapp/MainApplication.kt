package com.example.tictacapp

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "d843b89d-8a48-4ffb-ab03-9cc89d4c0196"

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}
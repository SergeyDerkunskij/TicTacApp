package com.example.tictacapp

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = ""// put here your ONESIGNAL ID

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
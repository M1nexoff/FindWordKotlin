package com.example.findwordkotlin.data.app

import android.app.Application
import com.example.findwordkotlin.domain.AppController

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppController.getInstance(this)
    }
}
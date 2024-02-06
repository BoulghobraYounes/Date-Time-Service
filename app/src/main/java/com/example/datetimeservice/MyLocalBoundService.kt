package com.example.datetimeservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyLocalBoundService: Service() {

    private val myLocalBinder = LocalBinder()

    override fun onBind(p0: Intent?): IBinder? {
        return myLocalBinder
    }

    public fun getUsDateAndTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        return dateFormat.format(Date())
    }

    inner class LocalBinder(): Binder() {
        fun getService(): MyLocalBoundService = this@MyLocalBoundService
    }
}
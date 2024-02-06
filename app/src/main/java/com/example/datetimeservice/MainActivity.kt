package com.example.datetimeservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var myLocalBoundService: MyLocalBoundService
    private var isConnected = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as MyLocalBoundService.LocalBinder
            myLocalBoundService = binder.getService()
            isConnected = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isConnected = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Intent(this, MyLocalBoundService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }

        val btn: Button = findViewById(R.id.button)
        val tv : TextView = findViewById(R.id.textView)

        btn.setOnClickListener {
            if (isConnected) {
                tv.text = myLocalBoundService.getUsDateAndTime()
            }
        }

    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isConnected = false
    }

}
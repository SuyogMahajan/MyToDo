package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val i = Intent(this,MainActivity::class.java)
        supportActionBar?.hide()

        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(i)
        },2500)

    }
}
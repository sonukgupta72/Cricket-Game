package com.sonukgupta72.cricketgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({ launchNextScreen() }, 2000)

    }

    private fun launchNextScreen() {
        val intent = Intent(this, PlayCricketActivity::class.java)
        startActivity(intent)
        finish()
    }
}

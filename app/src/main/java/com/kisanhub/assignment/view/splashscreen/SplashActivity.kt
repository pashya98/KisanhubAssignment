package com.kisanhub.assignment.view.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.kisanhub.assignment.view.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.splash)
        setTimeDelay()
    }

    private fun setTimeDelay() {
        try {
            val SPLASH_TIME_OUT: Long = 3000
            Handler().postDelayed({
                val i = Intent(this, DashboardActivity::class.java)
                startActivity(i)
                finish()
            }, SPLASH_TIME_OUT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
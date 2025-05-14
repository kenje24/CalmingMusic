package com.example.calmingmusic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Handling cold start (first time)
        if (savedInstanceState == null) {
            // Show splash screen
            // For example, set a layout or perform an animation
        }

        // Set the content view for splash screen (optional if you have a layout)
        setContentView(R.layout.activity_splash)

        // Transition to MainActivity after a delay (3 seconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // Move to the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish SplashActivity to prevent going back to it
        }, 3000) // Splash screen duration (3 seconds)
    }

    override fun onStart() {
        super.onStart()
        // Handle any warm start logic here if needed
    }

    override fun onResume() {
        super.onResume()
        // Handle any hot start logic here if needed
    }
}

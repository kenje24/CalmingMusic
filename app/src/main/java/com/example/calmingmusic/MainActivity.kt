package com.example.calmingmusic

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var button1: Button
    private lateinit var button2: Button
    private val handler = Handler(Looper.getMainLooper())
    private var stopRunnable: Runnable? = null
    private var isPlaying = false
    private var currentButton: Button? = null
    private var otherButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        button1.setOnClickListener {
            handleMusicToggle(button1, button2, R.raw.letmeknow)
        }

        button2.setOnClickListener {
            handleMusicToggle(button2, button1, R.raw.getlucky)
        }
    }

    private fun handleMusicToggle(clickedButton: Button, hiddenButton: Button, musicResId: Int) {
        if (!isPlaying) {
            currentButton = clickedButton
            otherButton = hiddenButton

            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(this, musicResId)

            clickedButton.text = "Stop Music"
            hiddenButton.visibility = View.GONE
            isPlaying = true

            mediaPlayer?.setOnCompletionListener {
                stopMusic()
            }

            mediaPlayer?.start()

            stopRunnable = Runnable {
                stopMusic()
            }
            handler.postDelayed(stopRunnable!!, 20000)
        } else {
            stopMusic()
        }
    }

    private fun stopMusic() {
        stopRunnable?.let { handler.removeCallbacks(it) }

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

        currentButton?.text = if (currentButton == button1) "Play Music 1" else "Play Music 2"
        currentButton = null

        button1.visibility = View.VISIBLE
        button2.visibility = View.VISIBLE

        isPlaying = false
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRunnable?.let { handler.removeCallbacks(it) }
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

package com.example.destroyersofbalon

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.destroyersofbalon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val player: MediaPlayer = MediaPlayer.create(this, R.raw.mp)
        player.stop()
        player.setLooping(true)
        player.setVolume(1.0f, 1.0f)
        player.start()
    }
}

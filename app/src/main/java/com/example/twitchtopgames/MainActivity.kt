package com.example.twitchtopgames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.twitchtopgames.databinding.ActivityMainBinding
import com.example.twitchtopgames.ui.GamesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.holderFragment, GamesFragment())
            .commit()
    }
}
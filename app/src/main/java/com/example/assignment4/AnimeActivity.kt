package com.example.assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.assignment4.databinding.ActivityAnimeBinding

class AnimeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val arr = intent.getStringArrayExtra("anime")
        binding.tvTitle.text = arr?.get(0)
        binding.tvCharacter.text = arr?.get(1)
        binding.tvQuote.text = arr?.get(2)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
}
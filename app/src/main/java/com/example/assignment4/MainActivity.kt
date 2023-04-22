package com.example.assignment4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assignment4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.list_movie -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ListFragment.newInstance()).commit()
                }
                R.id.saved_movie -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SavedFragment.newInstance()).commit()
                }
            }
            true
        }
        binding.bottomNav.selectedItemId = R.id.list_movie
    }
}
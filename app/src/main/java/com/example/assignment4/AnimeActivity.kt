package com.example.assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.assignment4.databinding.ActivityAnimeBinding
import com.example.assignment4.retrofit.AnimeAPI
import com.example.assignment4.retrofit.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(url).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val animeAPI = retrofit.create(AnimeAPI::class.java)

        val title = intent.getStringExtra("anime")
        binding.tvTitle.text = title
        CoroutineScope(Dispatchers.IO).launch {
            val anime = title?.let { animeAPI.getAnime(it).body()?.get(0) }
            runOnUiThread {
                if (anime != null) {
                    binding.tvCharacter.text = anime.character
                    binding.tvQuote.text = anime.quote
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
}
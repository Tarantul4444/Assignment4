package com.example.assignment4

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment4.adapter.AnimeAdapter
import com.example.assignment4.database.AnimeManager
import com.example.assignment4.databinding.FragmentListBinding
import com.example.assignment4.retrofit.Anime
import com.example.assignment4.retrofit.AnimeAPI
import com.example.assignment4.retrofit.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragment : Fragment(), AnimeAdapter.Listener {
    lateinit var binding: FragmentListBinding
    private var adapter = AnimeAdapter(this)
    val animes = arrayListOf<Anime>()
    private var animeManager: AnimeManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        binding.rcAnime.layoutManager = GridLayoutManager(context, 2)
        binding.rcAnime.adapter = adapter
        adapter.animeList = arrayListOf()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(url).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val animeAPI = retrofit.create(AnimeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {
//            val listTitles = animeAPI.getAllAnime().drop(4).subList(0, 3)
//            for(title in listTitles) {
//                val anime = animeAPI.getAnime(title).body()?.get(0)
//                if (anime != null) {
//                    adapter.addAnime(anime)
//                    animes.add(anime)
//                }
//                Log.i("msg", title)
//            }
            animes.add(Anime("1", "2", "3"))
            animes.add(Anime("3", "4", "5"))
            animes.add(Anime("5", "6", "7"))
        }
        Log.i("msg", animeManager.toString())
        animeManager!!.openDatabase()
        for(anime in animes) {
//            var checked = false
//            if(animeManager?.readDatabase()?.indexOf(anime) != -1) checked = true
            adapter.addAnime(anime)
        }
        animeManager!!.closeDatabase()
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        adapter.animeList = arrayListOf()
        animeManager!!.openDatabase()
        for(anime in animes) {
//            var checked = false
//            if(animeManager?.readDatabase()?.indexOf(anime) != -1) checked = true
            adapter.addAnime(anime)
        }
        animeManager!!.closeDatabase()
    }

    override fun onClick(anime: Anime) {
        val intent = Intent(activity, AnimeActivity::class.java)
        intent.putExtra("anime", arrayOf(anime.anime, anime.character, anime.quote))
        startActivity(intent)
    }

    override fun onSave(anime: Anime) {
        if(animeManager == null) return
        animeManager!!.openDatabase()
        animeManager!!.insertDatabase(anime)
        animeManager!!.closeDatabase()
    }

    override fun onUnsave(anime: Anime) {
        if(animeManager == null) return
        animeManager!!.openDatabase()
        animeManager!!.deleteDatabase(anime)
        animeManager!!.closeDatabase()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            animeManager = AnimeManager(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}
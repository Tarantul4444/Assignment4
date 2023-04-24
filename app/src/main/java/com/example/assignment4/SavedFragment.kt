package com.example.assignment4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment4.adapter.AnimeAdapter
import com.example.assignment4.adapter.SavedAdaptor
import com.example.assignment4.database.AnimeManager
import com.example.assignment4.databinding.FragmentListBinding
import com.example.assignment4.databinding.FragmentSavedBinding

class SavedFragment : Fragment(), AnimeAdapter.Listener {
    lateinit var binding: FragmentSavedBinding
    private var adapter = SavedAdaptor(this)
    private var animeManager: AnimeManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        animeManager = activity?.let { AnimeManager(it) }
        binding = FragmentSavedBinding.inflate(inflater)
        binding.rcAnime.layoutManager = GridLayoutManager(context, 2)
        binding.rcAnime.adapter = adapter
        if(animeManager != null) render()
        return binding.root
    }

    private fun render() {
        adapter.animeList = arrayListOf()
        animeManager?.openDatabase()
        for(anime in animeManager?.readDatabase()!!) adapter.addAnime(anime)
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
        fun newInstance() = SavedFragment()
    }

    override fun onClick(anime: String) {
        val intent = Intent(activity, AnimeActivity::class.java)
        intent.putExtra("anime", anime)
        startActivity(intent)
    }

    override fun onSave(anime: String) {
        if(animeManager == null) return
        animeManager!!.openDatabase()
        animeManager!!.deleteDatabase(anime)
        animeManager!!.closeDatabase()
        adapter.removeAnime(anime)
    }
}
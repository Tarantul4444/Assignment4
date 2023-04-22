package com.example.assignment4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.ListFragment
import com.example.assignment4.R
import com.example.assignment4.databinding.AnimeItemBinding
import com.example.assignment4.retrofit.Anime

class SavedAdaptor : RecyclerView.Adapter<SavedAdaptor.AnimeHolder>() {
    var animeList = ArrayList<Anime>()

    class AnimeHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = AnimeItemBinding.bind(item)
        fun bind(anime: Anime) {
            binding.animeTitle.text = anime.anime
            binding.animeSave.visibility = View.GONE
            binding.animeUnsave.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return AnimeHolder(view)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    override fun onBindViewHolder(holder: AnimeHolder, position: Int) {
        holder.bind(animeList[position])
    }

    fun addAnime(anime: Anime) {
        animeList.add(anime)
        notifyDataSetChanged()
    }
}
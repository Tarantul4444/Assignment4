package com.example.assignment4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.R
import com.example.assignment4.databinding.AnimeItemBinding
import com.example.assignment4.retrofit.Anime

class SavedAdaptor(val listener: AnimeAdapter.Listener) : RecyclerView.Adapter<SavedAdaptor.AnimeHolder>() {
    var animeList = ArrayList<String>()

    class AnimeHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = AnimeItemBinding.bind(item)
        fun bind(anime: String, listener: AnimeAdapter.Listener) {
            binding.animeTitle.text = anime
            binding.animeSave.text = "Delete"
            itemView.setOnClickListener {
                listener.onClick(anime)
            }
            binding.animeSave.setOnClickListener {
                listener.onSave(anime)
            }
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
        holder.bind(animeList[position], listener)
    }

    fun addAnime(anime: String) {
        animeList.add(anime)
        notifyDataSetChanged()
    }

    fun removeAnime(anime: String) {
        animeList.remove(anime)
        notifyDataSetChanged()
    }
}
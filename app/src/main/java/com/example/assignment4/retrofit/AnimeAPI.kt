package com.example.assignment4.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeAPI {
    @GET("available/anime")
    suspend fun getAllAnime(): List<String>

    @GET("quotes/anime")
    suspend fun getAnime(
        @Query("title") anime: String): Response<List<Anime>>
}
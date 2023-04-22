package com.example.assignment4.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.assignment4.retrofit.Anime

class AnimeManager(context: Context) {
    private val databaseHelper: AnimeHelper = AnimeHelper(context)
    lateinit var database: SQLiteDatabase

    fun openDatabase() {
        database = databaseHelper.writableDatabase
    }

    @SuppressLint("Range")
    fun readDatabase(): ArrayList<Anime> {
        val list = arrayListOf<Anime>()
        val cursor = database.query(Animes.TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while(cursor.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndex(Animes.COLUMN_TITLE))
                val character = cursor.getString(cursor.getColumnIndex(Animes.COLUMN_CHARACTER))
                val quote = cursor.getString(cursor.getColumnIndex(Animes.COLUMN_QUOTE))
                list.add(Anime(title, character, quote))
            }
        }
        cursor.close()
        return list
    }

    fun insertDatabase(anime: Anime) {
        val values = ContentValues().apply {
            put(Animes.COLUMN_TITLE, anime.anime)
            put(Animes.COLUMN_CHARACTER, anime.character)
            put(Animes.COLUMN_QUOTE, anime.quote)
        }
        database.insert(Animes.TABLE_NAME, null, values)
    }

    fun deleteDatabase(anime: Anime) {
        database.delete(Animes.TABLE_NAME, "Title = ?", arrayOf(anime.anime))
    }

    fun closeDatabase() {
        databaseHelper.close()
    }
}
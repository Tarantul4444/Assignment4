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
    fun readDatabase(): ArrayList<String> {
        val list = arrayListOf<String>()
        val cursor = database.query(Animes.TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while(cursor.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndex(Animes.COLUMN_TITLE))
                list.add(title)
            }
        }
        cursor.close()
        return list
    }

    @SuppressLint("Range")
    fun findDatabase(anime: String): Boolean {
        var str: String? = null
        val cursor = database.query(Animes.TABLE_NAME, null, "Title = ?", arrayOf(anime), null, null, null)
        with(cursor) {
            while(cursor.moveToNext()) str = cursor.getString(cursor.getColumnIndex(Animes.COLUMN_TITLE))
        }
        cursor.close()
        if(str != null) return true
        return false
    }

    fun insertDatabase(anime: String) {
        val values = ContentValues().apply {
            put(Animes.COLUMN_TITLE, anime)
        }
        database.insert(Animes.TABLE_NAME, null, values)
    }

    fun deleteDatabase(anime: String) {
        database.delete(Animes.TABLE_NAME, "Title = ?", arrayOf(anime))
    }

    fun closeDatabase() {
        databaseHelper.close()
    }
}
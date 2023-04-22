package com.example.assignment4.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AnimeHelper(context: Context): SQLiteOpenHelper(context, Animes.DATABASE_NAME,
    null, Animes.DATABASE_VERSION) {
    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(Animes.CREATE_TABLE)
    }

    override fun onUpgrade(database : SQLiteDatabase?, p1: Int, p2: Int) {
        database?.execSQL(Animes.DROP_TABLE)
        onCreate(database)
    }
}
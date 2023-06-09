package com.example.assignment4.database

import android.provider.BaseColumns

object Animes {
    const val TABLE_NAME = "Animes"
    const val COLUMN_TITLE = "Title"

    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "AnimesDatabase.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_TITLE TEXT);"

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}
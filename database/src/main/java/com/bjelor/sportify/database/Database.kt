package com.bjelor.sportify.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SportEntryEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class Database : RoomDatabase() {

    abstract fun sportEntryDao(): SportEntryDao

}
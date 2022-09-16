package com.bjelor.sportify.database.di

import androidx.room.Room
import com.bjelor.sportify.database.Database
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), Database::class.java, "sportify_database")
            .createFromAsset("intro_database.db")
            .build()
    }

    // region Dao

    single { get<Database>().sportEntryDao() }

    // endregion Dao
}
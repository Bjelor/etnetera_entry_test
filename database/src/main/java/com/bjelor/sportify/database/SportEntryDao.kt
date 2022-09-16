package com.bjelor.sportify.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SportEntryDao {

    @Query("SELECT * FROM sport_entry ORDER BY time DESC")
    fun getAllSportEntries(): Flow<List<SportEntryEntity>>

    @Insert
    suspend fun insertOrUpdateSportEntry(entry: SportEntryEntity)

    @Delete
    suspend fun delete(entry: SportEntryEntity)

    @Query("DELETE FROM sport_entry")
    suspend fun deleteAllSportEntries()

    @Query("DELETE FROM sport_entry WHERE id = :id")
    suspend fun deleteById(id: Int)
}

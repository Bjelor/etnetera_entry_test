package com.bjelor.sportify.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sport_entry")
data class SportEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    /**
     * Seconds
     */
    val duration: Int,
    val place: String,
    val time: String,
)

/*
INSERT INTO sport_entry VALUES
(1, "Jogging", 1500, "Brno, Wilson forest", "2022-09-11T18:30:00+0000"),
(2, "Jump rope", 600, "My home", "2022-09-12T08:30:00+0000"),
(3, "Hiking", 7200, "Palava", "2022-09-13T10:30:00+0000")
 */
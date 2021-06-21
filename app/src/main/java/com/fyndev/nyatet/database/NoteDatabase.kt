package com.fyndev.nyatet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fyndev.nyatet.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
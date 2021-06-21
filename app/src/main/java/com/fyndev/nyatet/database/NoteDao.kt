package com.fyndev.nyatet.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fyndev.nyatet.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM noteentity ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("DELETE FROM noteentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM noteentity WHERE title LIKE :title")
    fun getSearchResults(title: String): LiveData<List<NoteEntity>>
}
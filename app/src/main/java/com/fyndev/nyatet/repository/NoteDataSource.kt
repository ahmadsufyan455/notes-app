package com.fyndev.nyatet.repository

import androidx.lifecycle.LiveData
import com.fyndev.nyatet.database.NoteDao
import com.fyndev.nyatet.entity.NoteEntity

class NoteDataSource(private val noteDao: NoteDao) {
    fun getAllNotes(): LiveData<List<NoteEntity>> = noteDao.getAllNotes()
    suspend fun insertNote(noteEntity: NoteEntity) = noteDao.insert(noteEntity)
    suspend fun updateNote(noteEntity: NoteEntity) = noteDao.update(noteEntity)
    suspend fun deleteNote(noteEntity: NoteEntity) = noteDao.delete(noteEntity)
    suspend fun deleteAll() = noteDao.deleteAll()
    fun getSearchResults(title: String) = noteDao.getSearchResults(title)
}
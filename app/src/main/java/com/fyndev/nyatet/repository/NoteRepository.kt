package com.fyndev.nyatet.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.fyndev.nyatet.entity.NoteEntity

class NoteRepository(private val noteDataSource: NoteDataSource) {
    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return noteDataSource.getAllNotes()
    }

    suspend fun insertNote(noteEntity: NoteEntity) {
        noteDataSource.insertNote(noteEntity)
    }

    suspend fun updateNote(noteEntity: NoteEntity) {
        noteDataSource.updateNote(noteEntity)
    }

    suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDataSource.deleteNote(noteEntity)
    }

    suspend fun deleteAll() {
        noteDataSource.deleteAll()
    }

    @WorkerThread
    fun getSearch(title: String): LiveData<List<NoteEntity>> {
        return noteDataSource.getSearchResults(title)
    }

}
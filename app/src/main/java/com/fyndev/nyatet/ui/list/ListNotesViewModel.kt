package com.fyndev.nyatet.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyndev.nyatet.entity.NoteEntity
import com.fyndev.nyatet.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListNotesViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return noteRepository.getAllNotes()
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAll()
        }
    }
}
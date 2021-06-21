package com.fyndev.nyatet.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyndev.nyatet.entity.NoteEntity
import com.fyndev.nyatet.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateNoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    fun updateNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(noteEntity)
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(noteEntity)
        }
    }
}
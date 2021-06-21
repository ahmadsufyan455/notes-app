package com.fyndev.nyatet.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyndev.nyatet.entity.NoteEntity
import com.fyndev.nyatet.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    fun insertNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertNote(noteEntity)
        }
    }
}
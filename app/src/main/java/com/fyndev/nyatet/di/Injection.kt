package com.fyndev.nyatet.di

import androidx.room.Room
import com.fyndev.nyatet.database.NoteDatabase
import com.fyndev.nyatet.repository.NoteDataSource
import com.fyndev.nyatet.repository.NoteRepository
import com.fyndev.nyatet.ui.add.AddNoteViewModel
import com.fyndev.nyatet.ui.list.ListNotesViewModel
import com.fyndev.nyatet.ui.update.UpdateNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    factory { get<NoteDatabase>().noteDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NoteDatabase::class.java, "note.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { NoteDataSource(get()) }
    single { NoteRepository(get()) }
}

val viewModelModule = module {
    viewModel { AddNoteViewModel(get()) }
    viewModel { ListNotesViewModel(get()) }
    viewModel { UpdateNoteViewModel(get()) }
}
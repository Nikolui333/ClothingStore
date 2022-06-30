package com.semenovnikolay.clothingstore.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenovnikolay.clothingstore.domain.useCase.AddClothesUseCase
import kotlinx.coroutines.launch

class AddClothesViewModel(private val addClothesUseCase: AddClothesUseCase) : ViewModel() {

    val loadAddClothes = addClothesUseCase.loadAddClothes()
    // viewModelScope прекращает работу внутри ViewModel (в данном случае в методе insert) в случае, если пользователь покинул экран
    // проще говоря, если этот метод не используется, viewModelScope не загружает им память
    fun migration(context: Context) = viewModelScope.launch {
        addClothesUseCase.startMigration(context)
    }
}
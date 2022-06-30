package com.semenovnikolay.clothingstore.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.domain.repository.AddClothesCall

class AddClothesUseCase (private val addClothesCall: AddClothesCall) {

    // получение даннх с локальной базы данных
    fun loadAddClothes(): LiveData<List<AddLocalModel>> {

        return addClothesCall.loadMedicines()

    }
    // запуск меграций
    suspend fun startMigration (context: Context) {

        addClothesCall.startMigration(context)

    }
}
package com.semenovnikolay.clothingstore.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.domain.repository.AddClothesCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddClothesUseCase (private val addClothesCall: AddClothesCall) {

    // получение даннх с локальной базы данных
    fun loadAddClothes(): LiveData<List<AddLocalModel>> {

        return addClothesCall.loadAddClothes()

    }
    // запуск меграций
    suspend fun startMigration (context: Context) {

        addClothesCall.startMigration(context)

    }

    // изменение размера одежды
    suspend fun updateClothesSize(addLocalModel: AddLocalModel) {
        CoroutineScope(Dispatchers.IO).launch {
            addClothesCall.updateClothesSize(addLocalModel)}
    }
}
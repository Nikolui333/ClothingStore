package com.semenovnikolay.clothingstore.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.AddLocalModel

interface AddClothesCall {
    fun loadAddClothes(): LiveData<List<AddLocalModel>>
    suspend fun startMigration(context: Context)
    // обновление информации о размере одежды
    suspend fun updateClothesSize(addLocalModel: AddLocalModel)
}
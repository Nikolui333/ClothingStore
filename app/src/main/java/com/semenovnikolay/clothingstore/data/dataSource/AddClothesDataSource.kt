package com.semenovnikolay.clothingstore.data.dataSource

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.AddLocalModel

interface AddClothesDataSource {
    fun loadMedicines(): LiveData<List<AddLocalModel>>
}
package com.semenovnikolay.clothingstore.data.dataSource

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.AddLocalModel

interface AddClothesDataSource {
    fun insert(addLocalModel: AddLocalModel)
    fun loadAddClothes(): LiveData<List<AddLocalModel>>
}
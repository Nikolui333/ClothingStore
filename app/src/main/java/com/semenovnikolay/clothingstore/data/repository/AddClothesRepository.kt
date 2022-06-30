package com.semenovnikolay.clothingstore.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesApiDataSource
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesDataSource
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.domain.repository.AddClothesCall

class AddClothesRepository(private val addClothesApiDataSource: AddClothesApiDataSource,
                           private val addClothesDataSource: AddClothesDataSource
) : AddClothesCall {

    // загрузка данных из локальной базы данных
    override fun loadMedicines(): LiveData<List<AddLocalModel>> {
        return addClothesDataSource.loadMedicines()    }

    // метод меграций
    override suspend fun startMigration(context: Context) {
        addClothesApiDataSource.startMigration(context)
    }
}
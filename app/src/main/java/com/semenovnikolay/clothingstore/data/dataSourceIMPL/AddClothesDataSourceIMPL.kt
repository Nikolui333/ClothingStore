package com.semenovnikolay.clothingstore.data.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesDataSource
import com.semenovnikolay.clothingstore.data.localDB.AddClothesDAO
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddClothesDataSourceIMPL(private val dao:AddClothesDAO) : AddClothesDataSource {

    override fun insert(addLocalModel: AddLocalModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(addLocalModel)}
    }

    override fun loadAddClothes(): LiveData<List<AddLocalModel>> {
        return dao.loadAddClothes()
    }
}
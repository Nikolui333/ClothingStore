package com.semenovnikolay.clothingstore.data.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.semenovnikolay.clothingstore.data.models.AddLocalModel

@Dao
interface AddClothesDAO {
    // записывает данные в локальную базу данных с сервера
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(medicationsModel: AddLocalModel)
}
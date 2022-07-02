package com.semenovnikolay.clothingstore.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.semenovnikolay.clothingstore.data.models.AddLocalModel

@Database(entities=[AddLocalModel::class],version=1)
abstract class AddClothesDB: RoomDatabase(){
    abstract val addClothesDAO:AddClothesDAO
}

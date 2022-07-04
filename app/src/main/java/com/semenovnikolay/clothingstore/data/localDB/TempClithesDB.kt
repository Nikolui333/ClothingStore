package com.semenovnikolay.clothingstore.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.CardModel

@Database(entities=[CardModel::class, AddLocalModel::class], version=1)
abstract class TempClothesDB: RoomDatabase() {
    abstract val cardDao: CardDao
    abstract val addClothesDAO:AddClothesDAO
}
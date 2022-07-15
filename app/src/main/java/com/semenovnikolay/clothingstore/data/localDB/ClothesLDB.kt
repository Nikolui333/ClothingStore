package com.semenovnikolay.clothingstore.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel


@Database(entities=[CardModel::class, AddLocalModel::class, FavoriteModel::class], version=1)
abstract class ClothesLDB : RoomDatabase() {
    abstract val cardDao: CardDao
    abstract val addClothesDAO:AddClothesDAO
    abstract val favoriteDAO:FavoriteDAO

}

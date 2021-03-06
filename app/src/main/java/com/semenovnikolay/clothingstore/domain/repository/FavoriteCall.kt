package com.semenovnikolay.clothingstore.domain.repository

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel

interface FavoriteCall {
    // suspend приостанавливает поток после выплнения метода
    suspend fun insert(favoriteModel: FavoriteModel)

    fun loadClothesFromCard(): LiveData<List<FavoriteModel>>

    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<FavoriteModel>>

    // удаление товара из корзины
    suspend fun deleteProductFromCard(idProduct:Int)

    //  удаление товара из карточки товара
    suspend fun deleteProductToCardFromCardProduct(idProduct:String)

    suspend fun clear()

    suspend fun updateClothesSize(favoriteModel: FavoriteModel)
}
package com.semenovnikolay.clothingstore.domain.repository

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.CardModel

interface CardCall {
    // suspend приостанавливает поток после выплнения метода
    suspend fun insert(cardModel: CardModel)

    fun loadClothesFromCard(): LiveData<List<CardModel>>

    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>>

    // удаление товара из корзины
    suspend fun deleteProductFromCard(idProduct:Int)

    //  удаление товара из карточки товара
    suspend fun deleteProductToCardFromCardProduct(idProduct:String)

    suspend fun clear()
}
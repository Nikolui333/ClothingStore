package com.semenovnikolay.clothingstore.domain.useCase

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.domain.repository.CardCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardUseCase (private val cardCall: CardCall) {
    // добавление уникального товара в корзину
    suspend fun insert(cardModel: CardModel) {
        cardCall.insert(cardModel)    }
/*    // увеличение (или уменьшение) количества пачек одного из препоратов
    suspend fun updateProductToCard(cardModel: CardModel) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.updateProductToCard(cardModel)}
    }*/
    // отправка данный (заказа) на сервер
    fun loadClothesFromCard(): LiveData<List<CardModel>> {
        return cardCall.loadClothesFromCard()    }

    fun loadClothesToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>> {
        return cardCall.loadMedicineToCardFromCardProduct(idProduct)    }

    suspend fun deleteProductFromCard(idProduct:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.deleteProductFromCard(idProduct)}
    }

    suspend fun deleteProductToCardFromCardProduct(idProduct:String) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.deleteProductToCardFromCardProduct(idProduct)}
    }
    // очистка корзины
    suspend fun clear() {
        cardCall.clear()    }
}
package com.semenovnikolay.clothingstore.domain.useCase

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel
import com.semenovnikolay.clothingstore.domain.repository.CardCall
import com.semenovnikolay.clothingstore.domain.repository.FavoriteCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteUseCase (private val favoriteCall: FavoriteCall) {

        // добавление уникального товара в корзину
        suspend fun insert(favoriteModel: FavoriteModel) {
            favoriteCall.insert(favoriteModel)    }
        /*    // увеличение (или уменьшение) количества пачек одного из препоратов
            suspend fun updateProductToCard(cardModel: CardModel) {
                CoroutineScope(Dispatchers.IO).launch {
                    cardCall.updateProductToCard(cardModel)}
            }*/
        // отправка данный (заказа) на сервер
        fun loadClothesFromCard(): LiveData<List<FavoriteModel>> {
            return favoriteCall.loadClothesFromCard()    }

        fun loadClothesToCardFromCardProduct(idProduct:String): LiveData<List<FavoriteModel>> {
            return favoriteCall.loadMedicineToCardFromCardProduct(idProduct)    }

        suspend fun deleteProductFromCard(idProduct:Int) {
            CoroutineScope(Dispatchers.IO).launch {
                favoriteCall.deleteProductFromCard(idProduct)}
        }

        suspend fun deleteProductToCardFromCardProduct(idProduct:String) {
            CoroutineScope(Dispatchers.IO).launch {
                favoriteCall.deleteProductToCardFromCardProduct(idProduct)}
        }
        // очистка корзины
        suspend fun clear() {
            favoriteCall.clear()    }

    // изменение размера одежды
    suspend fun updateClothesSize(favoriteModel: FavoriteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteCall.updateClothesSize(favoriteModel)}
    }
}
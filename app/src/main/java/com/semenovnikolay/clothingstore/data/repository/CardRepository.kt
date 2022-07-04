package com.semenovnikolay.clothingstore.data.repository

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.localDB.CardDao
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.domain.repository.CardCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardRepository(private val dao: CardDao): CardCall {

    override suspend fun insert(cardModel: CardModel) {
        dao.insert(cardModel)    }

/*    // обновление информации о товаре, после изменения его количества
    override suspend fun updateProductToCard(cardModel: CardModel){
        dao.updateProductToCard(cardModel)
    }*/

    override fun loadClothesFromCard(): LiveData<List<CardModel>> {
        return dao.loadMedicineFromCard()    }

    override fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>> {
        return dao.loadMedicineToCardFromCardProduct(idProduct)    }

    // удаление товара из корзины
    override suspend fun deleteProductFromCard(idProduct:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProductFromCard(idProduct)}
    }

    //  удаление товара из карточки товара
    override suspend fun deleteProductToCardFromCardProduct(idProduct:String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProductToCardFromCardProduct(idProduct)}
    }

    override suspend fun clear() {
        dao.clear()    }
}
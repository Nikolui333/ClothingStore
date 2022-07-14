package com.semenovnikolay.clothingstore.data.repository

import androidx.lifecycle.LiveData
import com.semenovnikolay.clothingstore.data.localDB.FavoriteDAO
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel
import com.semenovnikolay.clothingstore.domain.repository.FavoriteCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteRepository (private val dao: FavoriteDAO): FavoriteCall {

    override suspend fun insert(favoriteModel: FavoriteModel) {
        dao.insert(favoriteModel)    }

/*    // обновление информации о товаре, после изменения его количества
    override suspend fun updateProductToCard(cardModel: CardModel){
        dao.updateProductToCard(cardModel)
    }*/

    override fun loadClothesFromCard(): LiveData<List<FavoriteModel>> {
        return dao.loadMedicineFromCard()    }

    override fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<FavoriteModel>> {
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

    override suspend fun updateClothesSize(favoriteModel: FavoriteModel) {
        dao.updateClothesSize(favoriteModel)
    }
}
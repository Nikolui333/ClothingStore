package com.semenovnikolay.clothingstore.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.semenovnikolay.clothingstore.data.models.CardModel

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cardModel: CardModel)

    // получение всех товаров из корзины
    @Query("SELECT * FROM card_data_table")
    fun loadMedicineFromCard(): LiveData<List<CardModel>>

    // отслеживаем наличие нужного товара в корзине
    @Query("SELECT * FROM card_data_table WHERE card_idProduct = :idProduct")
    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>>

    // удаление конкретного товара на экране корзины
    @Query("DELETE FROM card_data_table WHERE card_id = :idProductToCard")
    suspend fun deleteProductFromCard(idProductToCard:Int)

    // удаление конкретного товара на экране корзины, чтобы оно также удалалось на экране добавления в корзину
    @Query("DELETE FROM card_data_table WHERE card_idProduct = :idProduct")
    suspend fun deleteProductToCardFromCardProduct(idProduct:String)

    @Query("DELETE FROM card_data_table")
    suspend fun clear()
}
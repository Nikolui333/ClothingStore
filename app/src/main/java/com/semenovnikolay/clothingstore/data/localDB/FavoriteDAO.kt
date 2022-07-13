package com.semenovnikolay.clothingstore.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semenovnikolay.clothingstore.data.models.FavoriteModel

@Dao
interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteModel: FavoriteModel)

    // получение всех товаров из корзины
    @Query("SELECT * FROM favorite_data_table")
    fun loadMedicineFromCard(): LiveData<List<FavoriteModel>>

    // отслеживаем наличие нужного товара в корзине
    @Query("SELECT * FROM favorite_data_table WHERE favorite_idProduct = :idProduct")
    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<FavoriteModel>>

/*    @Update
    suspend fun updateProductToCard(cardModel: CardModel)*/

    // удаление конкретного товара на экране корзины
    @Query("DELETE FROM favorite_data_table WHERE favorite_id = :idProductToCard")
    suspend fun deleteProductFromCard(idProductToCard:Int)

    // удаление конкретного товара на экране корзины, чтобы оно также удалалось на экране добавления в корзину
    @Query("DELETE FROM favorite_data_table WHERE favorite_idProduct = :idProduct")
    suspend fun deleteProductToCardFromCardProduct(idProduct:String)

    @Query("DELETE FROM favorite_data_table")
    suspend fun clear()
}
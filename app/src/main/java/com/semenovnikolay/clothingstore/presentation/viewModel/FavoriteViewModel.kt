package com.semenovnikolay.clothingstore.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel
import com.semenovnikolay.clothingstore.domain.useCase.CardUseCase
import com.semenovnikolay.clothingstore.domain.useCase.FavoriteUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel (private val favoriteUseCase: FavoriteUseCase): ViewModel() {

    fun startInsert(nameProduct:String, imageProduct:String, priceProduct:String, idFavoriteProduct:String, /*countProduct:String,*/ size: String) {
        insert(
            FavoriteModel(0, nameProduct, imageProduct, priceProduct, idFavoriteProduct, /*countProduct,*/ size)
        )
    }
    // viewModelScope прекращает работу внутри ViewModel (в данном случае в методе insert) в случае, если пользователь покинул экран
    // метод insert отвечает за добавление нового вида товара в корзину
    private fun insert(favoriteModel: FavoriteModel) = viewModelScope.launch{
        favoriteUseCase.insert(favoriteModel)
    }
/*    // launch позволяет запускать методы в параллельных потоках
    // метод updateProductToCard меняет колличество конкретного вида товара в корзине
    fun updateProductToCard(cardModel: CardModel) = viewModelScope.launch{
        cardUseCase.updateProductToCard(cardModel)
    }*/

    // так как в методе loadClothesFromCard() класса CardUseCase нет принимаемых значений, вместо создания метода,
    // возвращающего метод loadClothesFromCard(), можно присвоить его переменной
    val loadClothesFromCard = favoriteUseCase.loadClothesFromCard()

    // LiveData хранит данные, которые можно получать каждый раз, когда что-то меняется
    // метод loadMedicineToCardFromCardProduct проверяет, есть ли товар в корзине
    fun loadMedicineToCardFromCardProduct(idFavoriteProduct:String): LiveData<List<FavoriteModel>> {
        return favoriteUseCase.loadClothesToCardFromCardProduct(idFavoriteProduct)
    }
    // методо deleteProductFromCard удаляет выбранный вид лекарства из корзины во вкладке корзины
    fun deleteProductFromCard(idProduct:Int) = viewModelScope.launch{

        favoriteUseCase.deleteProductFromCard(idProduct)
    }
    // метод deleteProductToCardFromCardProduct удаляет выбранный вид лекарства из корзины на вкладке со списком препаратов
    fun deleteProductToCardFromCardProduct(idProduct:String) = viewModelScope.launch{

        favoriteUseCase.deleteProductToCardFromCardProduct(idProduct)
    }
    // метод clearCard удаляет все товары из корзины
    fun clearCard() = viewModelScope.launch{

        favoriteUseCase.clear()
    }

    // launch позволяет запускать методы в параллельных потоках
    // метод updateClothesSize меняет размер одежды
    fun updateClothesSize(favoriteModel: FavoriteModel) = viewModelScope.launch{
        favoriteUseCase.updateClothesSize(favoriteModel)
    }
}
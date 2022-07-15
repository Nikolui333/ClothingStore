package com.semenovnikolay.clothingstore.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.domain.useCase.CardUseCase
import kotlinx.coroutines.launch

class CardViewModel (private val cardUseCase: CardUseCase): ViewModel() {

    fun startInsert(nameProduct:String, imageProduct:String, priceProduct:String, idProduct:String, /*countProduct:String,*/ size: String) {
        insert(
            CardModel(0, nameProduct, imageProduct, priceProduct, idProduct, /*countProduct,*/ size)
        )
    }
    // viewModelScope прекращает работу внутри ViewModel (в данном случае в методе insert) в случае, если пользователь покинул экран
    // метод insert отвечает за добавление нового вида товара в корзину
    private fun insert(cardModel: CardModel) = viewModelScope.launch{
        cardUseCase.insert(cardModel)
    }

    // так как в методе loadClothesFromCard() класса CardUseCase нет принимаемых значений, вместо создания метода,
    // возвращающего метод loadClothesFromCard(), можно присвоить его переменной
    val loadClothesFromCard = cardUseCase.loadClothesFromCard()

    // LiveData хранит данные, которые можно получать каждый раз, когда что-то меняется
    // метод loadMedicineToCardFromCardProduct проверяет, есть ли товар в корзине
    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>> {
        return cardUseCase.loadClothesToCardFromCardProduct(idProduct)
    }
    // методо deleteProductFromCard удаляет выбранный вид лекарства из корзины во вкладке корзины
    fun deleteProductFromCard(idProduct:Int) = viewModelScope.launch{

        cardUseCase.deleteProductFromCard(idProduct)
    }
    // метод deleteProductToCardFromCardProduct удаляет выбранный вид лекарства из корзины на вкладке со списком препаратов
    fun deleteProductToCardFromCardProduct(idProduct:String) = viewModelScope.launch{

        cardUseCase.deleteProductToCardFromCardProduct(idProduct)
    }
    // метод clearCard удаляет все товары из корзины
    fun clearCard() = viewModelScope.launch{

        cardUseCase.clear()
    }

}
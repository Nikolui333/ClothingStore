package com.semenovnikolay.clothingstore.presentation.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.databinding.FragmentFavoriteBinding
import com.semenovnikolay.clothingstore.presentation.viewModel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel
import com.semenovnikolay.clothingstore.presentation.viewModel.CardViewModel

class Favorite : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private var favoriteAdapter: FavoriteAdapter? = null
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        initRecyclerCard()
        loadClothesFromCard()

        // Inflate the layout for this fragment
        return binding?.root
    }

    // инициализация
    private fun initRecyclerCard() {

        binding?.catalogClothes?.layoutManager =
            LinearLayoutManager(context)
        favoriteAdapter =
            FavoriteAdapter({ favoriteModel: FavoriteModel ->
                deleteFromFavorite(
                    favoriteModel
                )
            },
                { favoriteModel: /*AddLocalModel*/FavoriteModel ->
                    addToCard(favoriteModel)
                },
                { favoriteModel: /*AddLocalModel*/FavoriteModel ->
                    removeFromCard(
                        favoriteModel
                    )
                },
                { idProduct:Int, addToBasket: AppCompatImageButton,
                  removeFromBasket: AppCompatImageButton ->
                    loadClothesToCardFromCardProduct(
                        idProduct, addToBasket, removeFromBasket
                    )
                },
                { favoriteModel: FavoriteModel ->
                    lessSize(
                        favoriteModel
                    )
                },
                { favoriteModel: FavoriteModel ->
                    moreSize(
                        favoriteModel
                    )
                }
            )
        binding?.catalogClothes?.adapter = favoriteAdapter
    }

    private fun loadClothesFromCard() {

        favoriteViewModel.loadClothesFromCard.observe(viewLifecycleOwner, Observer {
            favoriteAdapter?.setList(it)
            favoriteAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteFromFavorite(favoriteModel: FavoriteModel){

        favoriteViewModel.deleteProductFromCard(favoriteModel.id)
    }

    // добавление товара в корзину
    private fun addToCard(favoriteModel: FavoriteModel) {
        cardViewModel.startInsert(favoriteModel.name,
            favoriteModel.image,
            favoriteModel.price,
            favoriteModel.id.toString(),
            favoriteModel.size
        )
    }

    // удаление товара из корзины
    private fun removeFromCard(favoriteModel: /*AddLocalModel*/FavoriteModel) {
        cardViewModel.deleteProductToCardFromCardProduct(favoriteModel.id.toString())
    }

    private fun loadClothesToCardFromCardProduct (idProduct:Int, addToBasket: AppCompatImageButton,
                                                  removeFromBasket: AppCompatImageButton){

        // передаём id, который приходит из адаптера
        cardViewModel.loadMedicineToCardFromCardProduct(idProduct.toString()).observe(viewLifecycleOwner, Observer {

            // в переменную count получаем колличество товара
            val count = it.count() // it - это неявное имя одного параметра в лямбда-функции

            // если колличество больше нуля, убрать кнопку добавления и отобразить кнопку удаления
            if (count>0) {
                addToBasket.visibility = View.GONE
                removeFromBasket.visibility = View.VISIBLE
            }
            else {
                addToBasket.visibility = View.VISIBLE
                removeFromBasket.visibility = View.GONE }
        })

    }

    // уменьшение колличества единиц товара
    private fun lessSize(favoriteModel:FavoriteModel) {

        var size: Int = favoriteModel.size.toInt()
        size--
        if (size<1) { // если size<40 вывести 40
            favoriteViewModel.updateClothesSize(
                FavoriteModel(favoriteModel.id, favoriteModel.name,
                    favoriteModel.image, /*favoriteModel.description, favoriteModel.discount,*/ favoriteModel.price, favoriteModel.idFavoriteProduct, "1")
            )

        }
        else {

            favoriteViewModel.updateClothesSize(
                FavoriteModel(favoriteModel.id, favoriteModel.name,
                    favoriteModel.image, /*favoriteModel.description, favoriteModel.discount,*/ favoriteModel.price, favoriteModel.idFavoriteProduct, size.toString())
            )

        }
    }

    // увеличение колличества единиц товара
    private fun moreSize(favoriteModel:FavoriteModel) {

        // получаем колличество товара
        var size: Int = favoriteModel.size.toInt()
        size++

        favoriteViewModel.updateClothesSize(
            FavoriteModel(favoriteModel.id, favoriteModel.name,
                favoriteModel.image, favoriteModel.price, favoriteModel.idFavoriteProduct, size.toString()
            ))
    }
}
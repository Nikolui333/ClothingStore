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
import com.semenovnikolay.clothingstore.databinding.FragmentAddClothesBinding
import androidx.lifecycle.Observer
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.presentation.viewModel.AddClothesViewModel
import com.semenovnikolay.clothingstore.presentation.viewModel.CardViewModel
import com.semenovnikolay.clothingstore.presentation.viewModel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddClothes : Fragment() {

    private var binding: FragmentAddClothesBinding? = null
    private var addAdapter : AddAdapter? = null

    private val addClothesViewModel: AddClothesViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_clothes, container, false)

        setUpAdapter()
        loadClothes()

        return binding?.root
    }

  //  @SuppressLint("UseRequireInsteadOfGet")
    private fun setUpAdapter() {
        binding?.catalogClothes?.layoutManager = LinearLayoutManager(context)
        addAdapter = AddAdapter({ addLocalModel: AddLocalModel ->
            addToCard(addLocalModel)
        },
            {addLocalModel: AddLocalModel ->
                addToFavorite(addLocalModel)
            },
            { addLocalModel: AddLocalModel ->
            removeFromCard(
                addLocalModel
            )
        },
            {addLocalModel: AddLocalModel ->
                removeFromFavorite(addLocalModel)
            },
            { idProduct:Int, idFavoriteProduct:Int, addToBasket: AppCompatImageButton,
              removeFromBasket: AppCompatImageButton,  addToFavoriteTab:AppCompatImageButton, removeFromFavorite:AppCompatImageButton ->
                loadClothesToCardFromCardProduct(
                    idProduct, idFavoriteProduct, addToBasket, removeFromBasket, addToFavoriteTab, removeFromFavorite
                )
            },
            { addLocalModel: AddLocalModel ->
            lessSize(
                addLocalModel
            )
        }) { addLocalModel: AddLocalModel ->
            moreSize(
                addLocalModel
            )
        }

      binding?.catalogClothes?.adapter = addAdapter

    }

    private fun loadClothes() {

        // получение всех необходимых данных для заполнения recyclerView
        addClothesViewModel.loadAddClothes.observe(viewLifecycleOwner, Observer {
            // setList наполняет адаптер данными
            addAdapter?.setList(it)
            // notifyDataSetChanged обновляет адаптер
            addAdapter?.notifyDataSetChanged()
        })
    }

    // уменьшение колличества единиц товара
    private fun lessSize(addLocalModel:AddLocalModel) {

        var size: Int = addLocalModel.size.toInt()
        size--
        if (size<1) { // если size<40 вывести 40
            addClothesViewModel.updateClothesSize(
                AddLocalModel(addLocalModel.id, addLocalModel.name,
                    addLocalModel.image, addLocalModel.description, addLocalModel.discount, addLocalModel.price, "1")
            )

        }
        else {

            addClothesViewModel.updateClothesSize(
                AddLocalModel(addLocalModel.id, addLocalModel.name,
                    addLocalModel.image, addLocalModel.description, addLocalModel.discount, addLocalModel.price, size.toString())
            )

        }
    }

    // добавление товара в корзину
    private fun addToCard(addLocalModel: AddLocalModel
    ) {
        cardViewModel.startInsert(addLocalModel.name,
            addLocalModel.image,
            addLocalModel.price,
            addLocalModel.id.toString(),
            addLocalModel.size
        )
    }

    // добавление товара в корзину
    private fun addToFavorite(addLocalModel: AddLocalModel
    ) {
        favoriteViewModel.startInsert(addLocalModel.name,
            addLocalModel.image,
            addLocalModel.price,
            addLocalModel.id.toString(),
            addLocalModel.size
        )
    }

    // удаление товара из корзины
    private fun removeFromCard(addLocalModel: AddLocalModel) {
        cardViewModel.deleteProductToCardFromCardProduct(addLocalModel.id.toString())
    }

    // удаление товара из корзины
    private fun removeFromFavorite(addLocalModel: AddLocalModel) {
        favoriteViewModel.deleteProductToCardFromCardProduct(addLocalModel.id.toString())
    }

    private fun loadClothesToCardFromCardProduct (idProduct:Int, idFavoriteProduct:Int, addToBasket: AppCompatImageButton,
                                                   removeFromBasket: AppCompatImageButton, addToFavorite: AppCompatImageButton,
                                                  removeFromFavorite: AppCompatImageButton
    ){
        // передаём id, который приходит из адаптера
        favoriteViewModel.loadMedicineToCardFromCardProduct(idFavoriteProduct.toString()).observe(viewLifecycleOwner, Observer {

            // в переменную count получаем колличество товара
            val count = it.count() // it - это неявное имя одного параметра в лямбда-функции

            // если колличество больше нуля, убрать кнопку добавления и отобразить кнопку удаления
            if (count>0) {
                addToFavorite.visibility = View.GONE
                removeFromFavorite.visibility = View.VISIBLE
            }
            else {
                addToFavorite.visibility = View.VISIBLE
                removeFromFavorite.visibility = View.GONE }
        })

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

    // увеличение колличества единиц товара
    private fun moreSize(addLocalModel:AddLocalModel) {

        // получаем колличество товара
        var size: Int = addLocalModel.size.toInt()
        size++

        addClothesViewModel.updateClothesSize(
            AddLocalModel(addLocalModel.id, addLocalModel.name,
                addLocalModel.image, addLocalModel.description, addLocalModel.discount, addLocalModel.price, /*addLocalModel.idProduct,*/ size.toString()
        ))
    }

}
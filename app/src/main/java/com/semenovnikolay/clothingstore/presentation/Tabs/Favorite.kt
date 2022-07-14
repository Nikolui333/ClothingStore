package com.semenovnikolay.clothingstore.presentation.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                { addLocalModel: /*AddLocalModel*/FavoriteModel ->
                    addToCard(addLocalModel)
                },
                { addLocalModel: /*AddLocalModel*/FavoriteModel ->
                    removeFromCard(
                        addLocalModel
                    )
                })
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
    private fun addToCard(addLocalModel: /*AddLocalModel*/FavoriteModel/*, addToBasket: AppCompatImageButton,
                          removeFromBasket: AppCompatImageButton*/
    ) {
        cardViewModel.startInsert(addLocalModel.name,
            addLocalModel.image,
            addLocalModel.price,
            addLocalModel.id.toString(),
            /*"1",*/
            addLocalModel.size
        )
/*        addToBasket.visibility = View.GONE
        removeFromBasket.visibility = View.VISIBLE*/
    }

    // удаление товара из корзины
    private fun removeFromCard(addLocalModel: /*AddLocalModel*/FavoriteModel) {
        cardViewModel.deleteProductToCardFromCardProduct(addLocalModel.id.toString())
    }
}
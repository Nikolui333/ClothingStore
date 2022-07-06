package com.semenovnikolay.clothingstore.presentation.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.databinding.FragmentShoppingCartBinding
import com.semenovnikolay.clothingstore.presentation.viewModel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenovnikolay.clothingstore.data.models.CardModel

class ShoppingCart : Fragment()/*,View.OnClickListener*/ {

    private var binding: FragmentShoppingCartBinding? = null
    private var cardAdapter: ShoppingCartAdapter? = null
    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_cart, container, false)
        initRecyclerCard()
        loadClothesFromCard()

        return binding?.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    // инициализация
    private fun initRecyclerCard() {

        binding?.listCard?.layoutManager =
            LinearLayoutManager(context)
        cardAdapter =
            ShoppingCartAdapter ({ cardModel: CardModel ->
                deleteFromCard(
                    cardModel
                )
            })
        binding?.listCard?.adapter = cardAdapter
    }

    // загрузка всех товаров из корзины
    private fun loadClothesFromCard() {

        cardViewModel.loadClothesFromCard.observe(viewLifecycleOwner, Observer {
            cardAdapter?.setList(it)
            cardAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteFromCard(cardModel: CardModel){

        cardViewModel.deleteProductFromCard(cardModel.id)
    }

}
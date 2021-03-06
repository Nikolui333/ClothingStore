package com.semenovnikolay.clothingstore.presentation.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.databinding.FragmentShoppingCartBinding
import com.semenovnikolay.clothingstore.presentation.viewModel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenovnikolay.clothingstore.data.models.CardModel

class ShoppingCart : Fragment() {

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

        // очистка корзины
        binding?.clearCard?.setOnClickListener {
            cardViewModel.clearCard()
        }
            // отправка заказа
        binding?.checkoutCard?.setOnClickListener {
            val checkout = Checkout()
            checkout.show((context as FragmentActivity).supportFragmentManager, "checkout")
        }

        return binding?.root
    }

    // инициализация
    private fun initRecyclerCard() {

        binding?.listCard?.layoutManager =
            LinearLayoutManager(context)
        cardAdapter =
            ShoppingCartAdapter { cardModel: CardModel ->
                deleteFromCard(
                    cardModel
                )
            }
        binding?.listCard?.adapter = cardAdapter
    }

    // загрузка всех товаров из корзины
    private fun loadClothesFromCard() {

        cardViewModel.loadClothesFromCard.observe(viewLifecycleOwner, Observer {
            cardAdapter?.setList(it)
            cardAdapter?.notifyDataSetChanged()

            val total:Int = it.sumOf<CardModel>
            // суммировать поля price
            { it.price.toInt() }

            binding?.totalOrder?.text = total.toString()
        })
    }

    private fun deleteFromCard(cardModel: CardModel){

        cardViewModel.deleteProductFromCard(cardModel.id)
    }
}
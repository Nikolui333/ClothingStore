package com.semenovnikolay.clothingstore.presentation.Tabs

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.presentation.viewModel.CardViewModel
import com.semenovnikolay.clothingstore.presentation.viewModel.OrderApiViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.databinding.FragmentCheckoutListDialogBinding

class Checkout : BottomSheetDialogFragment() {

    private var binding: FragmentCheckoutListDialogBinding? = null
    private val cardViewModel: CardViewModel by viewModel()
  //  private val orderLocalViewModel: OrderLocalViewModel by viewModel()
    // viewModel, который отвечает за отправку данных на сервер
    private val orderApiViewModel: OrderApiViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout_list_dialog, container, false)
        binding?.submitCheckout?.setOnClickListener(View.OnClickListener {

            cardViewModel.loadClothesFromCard.observe(viewLifecycleOwner, Observer {
                // сумма за весь заказ
                val totalOrder:Int = it.sumOf<CardModel> { it.price/*totalPrice*/.toInt() }
                // заполнение данных для истории покупок
                // joinToString нужно чтобы выводить массив без квадратных скобок
                val descriptionOrder = it.map { it.name + /*": количество - " + it.count +*/ ", цена - " + it.price/*totalPrice*/ + " р; " }.joinToString("")

                // отправка данных на сервер
                orderApiViewModel.insert((context as FragmentActivity), binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(), descriptionOrder,
                    totalOrder.toString())

                // очистка текстовых полей
                binding?.enterNameCheckout?.setText("")
                binding?.enterPhoneCheckout?.setText("")

                // закрытие всплывающей панели
                dismiss()
                // очистка корзины
                cardViewModel.clearCard()

            })
        })
        return binding?.root
    }

}
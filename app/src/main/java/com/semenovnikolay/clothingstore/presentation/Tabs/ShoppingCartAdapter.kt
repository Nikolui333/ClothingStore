package com.semenovnikolay.clothingstore.presentation.Tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.databinding.CardItemBinding
import com.squareup.picasso.Picasso

class ShoppingCartAdapter(private val deleteFromCard:(CardModel)->Unit) :
    RecyclerView.Adapter<ShoppingCartAdapter.CardHolder>() {

    private val productsFromCard = ArrayList<CardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsFromCard.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(productsFromCard[position], deleteFromCard)

    }

    fun setList(cardList: List<CardModel>) {
        productsFromCard.clear()
        productsFromCard.addAll(cardList)

    }

    class CardHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            cardModel: CardModel, deleteFromCard: (CardModel) -> Unit
        ) {

            val getImage = cardModel.image
            Picasso.get().load(getImage).into(binding.imageProductCard)
            binding.nameProductCard.text = cardModel.name
            binding.sizeProductInBasket.text = cardModel.size
            binding.priceProductCard.text = cardModel.price
           // binding.totalPriceProductCard.text = cardModel.totalPrice

            binding.removeFromCardProductCard.setOnClickListener(View.OnClickListener {
                deleteFromCard(cardModel) // удаление из карточки, когда пользоваетль находится в корзине
            })

        }
    }
}
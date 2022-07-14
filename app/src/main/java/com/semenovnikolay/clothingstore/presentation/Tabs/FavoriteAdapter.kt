package com.semenovnikolay.clothingstore.presentation.Tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel
import com.semenovnikolay.clothingstore.databinding.FavoriteItemBinding
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val deleteFromFavorite:(FavoriteModel)->Unit, private val addToCard: (/*AddLocalModel*/FavoriteModel) -> Unit,
                      private val removeFromCard: (/*AddLocalModel*/FavoriteModel) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    private val productsFromFavorite = ArrayList<FavoriteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FavoriteItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.favorite_item, parent, false)
        return FavoriteAdapter.FavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(productsFromFavorite[position], deleteFromFavorite, addToCard, removeFromCard)
    }

    override fun getItemCount(): Int {
        return productsFromFavorite.size
    }

    fun setList(favoriteList: List<FavoriteModel>) {
        productsFromFavorite.clear()
        productsFromFavorite.addAll(favoriteList)

    }

    class FavoriteHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            favoriteModel: FavoriteModel, deleteFromFavorite: (FavoriteModel) -> Unit, addToCard: (/*AddLocalModel*/FavoriteModel) -> Unit,
            removeFromCard: (/*AddLocalModel*/FavoriteModel) -> Unit
        ) {

            val getImage = favoriteModel.image
            Picasso.get().load(getImage).into(binding.imageClothes
            /*imageProductCard*/)
            binding.nameClothes/*nameProductCard*/.text = favoriteModel.name
            binding.sizeClothes
            /*sizeProductInBasket*/.text = favoriteModel.size
            binding.priceClothes
            /*priceProductCard*/.text = favoriteModel.price

            binding.
            removeFavorite.setOnClickListener(View.OnClickListener {
                deleteFromFavorite(favoriteModel) // удаление из карточки, когда пользоваетль находится во вкладке избранное
            })

            binding.addToCard.setOnClickListener(View.OnClickListener {
                addToCard.invoke(/*addLocalModel*/favoriteModel)
            })

            binding.removeFromCard.setOnClickListener(View.OnClickListener {
                removeFromCard(/*addLocalModel*/favoriteModel)
            })
        }
    }



}
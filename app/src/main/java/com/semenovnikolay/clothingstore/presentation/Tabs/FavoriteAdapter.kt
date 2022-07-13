package com.semenovnikolay.clothingstore.presentation.Tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel
import com.semenovnikolay.clothingstore.databinding.FavoriteItemBinding
import com.squareup.picasso.Picasso

class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    private val productsFromFavorite = ArrayList<FavoriteModel>()

    class FavoriteHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            favoriteModel: FavoriteModel, /*deleteFromCard: (FavoriteModel) -> Unit*/
        ) {

            val getImage = favoriteModel.image
            Picasso.get().load(getImage).into(binding.imageClothes
            /*imageProductCard*/)
            binding.nameClothes/*nameProductCard*/.text = favoriteModel.name
            binding.sizeClothes
            /*sizeProductInBasket*/.text = favoriteModel.size
            binding.priceClothes
            /*priceProductCard*/.text = favoriteModel.price

       /*     binding.
            removeFromCardProductCard.setOnClickListener(View.OnClickListener {
                deleteFromCard(cardModel) // удаление из карточки, когда пользоваетль находится в корзине
            })
*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FavoriteItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.favorite_item, parent, false)
        return FavoriteAdapter.FavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(productsFromFavorite[position]/*, deleteFromCard*/)
    }

    override fun getItemCount(): Int {
        return productsFromFavorite.size
    }

    fun setList(favoriteList: List<FavoriteModel>) {
        productsFromFavorite.clear()
        productsFromFavorite.addAll(favoriteList)

    }

}
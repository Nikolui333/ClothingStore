package com.semenovnikolay.clothingstore.presentation.Tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.data.models.FavoriteModel
import com.semenovnikolay.clothingstore.databinding.FavoriteItemBinding
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val deleteFromFavorite:(FavoriteModel)->Unit, private val addToCard: (FavoriteModel) -> Unit,
                      private val removeFromCard: (FavoriteModel) -> Unit,
                      private val loadClothesToCardFromCardProduct:(Int, AppCompatImageButton, AppCompatImageButton)->Unit,
                      private val lessCount: (FavoriteModel) -> Unit,
                      private val moreCount:(FavoriteModel)->Unit) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    private val productsFromFavorite = ArrayList<FavoriteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FavoriteItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.favorite_item, parent, false)
        return FavoriteAdapter.FavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(productsFromFavorite[position], deleteFromFavorite, addToCard, removeFromCard, loadClothesToCardFromCardProduct, moreCount, lessCount)
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
            removeFromCard: (/*AddLocalModel*/FavoriteModel) -> Unit, loadClothesToCardFromCardProduct:(Int, AppCompatImageButton, AppCompatImageButton)->Unit,
            moreCount: (FavoriteModel) -> Unit, lessCount: (FavoriteModel) -> Unit
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

            loadClothesToCardFromCardProduct(/*addLocalModel*/favoriteModel.id,  binding.addToCard, binding.removeFromCard)

            binding.moreProductBasket.setOnClickListener(View.OnClickListener {
                moreCount(favoriteModel) //увеличить колличество единиц товара

            })

            binding.lessProductBasket.setOnClickListener(View.OnClickListener {
                lessCount(favoriteModel) // уменьшить колличество единиц товара

            })
        }
    }
}
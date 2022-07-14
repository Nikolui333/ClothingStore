package com.semenovnikolay.clothingstore.presentation.Tabs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.databinding.AddClothingItemBinding
import com.semenovnikolay.clothingstore.presentation.di.add
import com.squareup.picasso.Picasso

class AddAdapter(private val addToCard: (AddLocalModel/*, AppCompatImageButton, AppCompatImageButton*/) -> Unit,
                 private val addToFavorite: (AddLocalModel/*, AppCompatImageButton, AppCompatImageButton*/) -> Unit,
                 private val removeFromCard:(AddLocalModel)->Unit,
                 private val loadClothesToCardFromCardProduct:(Int, Int, AppCompatImageButton, AppCompatImageButton, AppCompatImageButton, AppCompatImageButton)->Unit,
                 private val lessCount: (AddLocalModel) -> Unit,
                 private val moreCount:(AddLocalModel)->Unit)
    : RecyclerView.Adapter<AddAdapter.AddHolder>() {

    private var add = ArrayList<AddLocalModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAdapter.AddHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
      //  val binding = AddClothingItemBinding.inflate(LayoutInflater.from(context),parent,false)
        val binding: AddClothingItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.add_clothing_item, parent, false)
        return AddHolder(binding)
    }

    override fun onBindViewHolder(holder: AddHolder, position: Int) {
        holder.bind(add[position], addToCard, addToFavorite, removeFromCard, loadClothesToCardFromCardProduct, moreCount, lessCount)
    }

    override fun getItemCount(): Int {
        return add.size
    }

    class AddHolder(val bind: AddClothingItemBinding) : RecyclerView.ViewHolder(bind.root) {

        private val binding : AddClothingItemBinding = bind

        fun bind(addLocalModel: AddLocalModel, addToCard: (AddLocalModel) -> Unit,
                 addToFavorite: (AddLocalModel) -> Unit,
                 removeFromCard: (AddLocalModel) -> Unit,
                 loadClothesToCardFromCardProduct: (Int, Int, AppCompatImageButton, AppCompatImageButton, AppCompatImageButton, AppCompatImageButton) -> Unit,
                 moreCount: (AddLocalModel) -> Unit, lessCount: (AddLocalModel) -> Unit){
            // получаем ссылку на изображение
            val getImage = addLocalModel.image
            // получаем изображение, которое находится по ссылке и добавляем его в imageClothes
            Picasso.get().load(getImage).into(binding.imageClothes)
            binding.nameClothes.text = addLocalModel.name
            binding.descriptionClothes.text = addLocalModel.description
            binding.discountClothes.text = addLocalModel.discount
            binding.priceClothes.text = addLocalModel.price
            binding.sizeClothes.text = addLocalModel.size

            binding.addToCard.setOnClickListener(View.OnClickListener {
                addToCard.invoke(addLocalModel)
            })

            binding.addToFavorite.setOnClickListener(View.OnClickListener {
                addToFavorite.invoke(addLocalModel)
            })

            binding.removeFromCard.setOnClickListener(View.OnClickListener {

                removeFromCard(addLocalModel)

            })
                                                                // ???
            loadClothesToCardFromCardProduct(addLocalModel.id, addLocalModel.id, binding.addToCard, binding.removeFromCard, binding.addToFavorite, binding.removeFavorite)

            binding.moreProductBasket.setOnClickListener(View.OnClickListener {
                moreCount(addLocalModel) //увеличить колличество единиц товара

            })

            binding.lessProductBasket.setOnClickListener(View.OnClickListener {
                lessCount(addLocalModel) // уменьшить колличество единиц товара

            })
        }

    }

    fun setList(addLocalList: List<AddLocalModel>) {
        add.clear()
        add.addAll(addLocalList) // заполнение add данными
    }
}
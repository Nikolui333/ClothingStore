package com.semenovnikolay.clothingstore.presentation.Tabs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.databinding.AddClothingItemBinding
import com.semenovnikolay.clothingstore.databinding.AddClothingItemBindingImpl

class AddAdapter
    (private val context: Context/*,
                  private val addCl: List<AddLocalModel>*/)
    : RecyclerView.Adapter<AddAdapter.AddHolder>() {

    private var add = ArrayList<AddLocalModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAdapter.AddHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
      //  val binding = AddClothingItemBinding.inflate(LayoutInflater.from(context),parent,false)
        val binding: AddClothingItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.add_clothing_item, parent, false)
        return AddHolder(binding)
    }

    override fun onBindViewHolder(holder: /*AddAdapter.*/AddHolder, position: Int) {
        holder.bind(add[position])
    }

    override fun getItemCount(): Int {
        return add.size
    }

    class AddHolder(val bind: AddClothingItemBinding) : RecyclerView.ViewHolder(bind.root) {

        private val binding : AddClothingItemBinding = bind

        fun bind(list: AddLocalModel/*, string: String*/){

            binding.foodItemNameTV.text = list.name
        }

    }

    fun setList(addLocalList: List<AddLocalModel>) {
        add.clear()
        add.addAll(addLocalList) // заполнение medications данными
    }


}
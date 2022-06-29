package com.semenovnikolay.clothingstore.presentation.Tabs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.databinding.AddClothingItemBinding
import com.semenovnikolay.clothingstore.databinding.AddClothingItemBindingImpl

class AddAdapter (private val context: Context,  private val names: List<String>) : RecyclerView.Adapter<AddAdapter.AddHolder>() {

   // private var add = ArrayList</*AddLocalModel*/String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAdapter.AddHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AddClothingItemBinding.inflate(LayoutInflater.from(context),parent,false)
      //  val binding: AddClothingItemBindingImpl = DataBindingUtil.inflate(layoutInflater, R.layout.add_clothing_item, parent, false)
        return AddHolder(binding)
    }

    override fun onBindViewHolder(holder: AddAdapter.AddHolder, position: Int) {
        holder.bind(/*add*/names[position]/*, string*/)
    }

    override fun getItemCount(): Int {
        return /*add*/names.size
    }

    class AddHolder(val bind: AddClothingItemBinding) : RecyclerView.ViewHolder(bind.root) {

        private val binding : AddClothingItemBinding = bind

        fun bind(list: String/*, string: String*/){

            binding.foodItemNameTV.text = list
        }

    }


}
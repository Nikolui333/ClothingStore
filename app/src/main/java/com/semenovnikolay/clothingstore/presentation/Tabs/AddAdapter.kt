package com.semenovnikolay.clothingstore.presentation.Tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.databinding.CardItemBinding

class AddAdapter : RecyclerView.Adapter<AddAdapter.AddHolder>() {

    private var add = ArrayList<AddLocalModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAdapter.AddHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.add_item, parent, false)
        return AddHolder(binding)
    }

    override fun onBindViewHolder(holder: AddAdapter.AddHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return add.size
    }

    class AddHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}
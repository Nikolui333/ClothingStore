package com.semenovnikolay.clothingstore.presentation.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.data.models.CardModel
import com.semenovnikolay.clothingstore.databinding.FragmentFavoriteBinding
import com.semenovnikolay.clothingstore.presentation.viewModel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer

class Favorite : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private var favoriteAdapter: FavoriteAdapter? = null
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        initRecyclerCard()
        loadClothesFromCard()

        // Inflate the layout for this fragment
        return binding?.root
    }

    // инициализация
    private fun initRecyclerCard() {

        binding?.catalogClothes?.layoutManager =
            LinearLayoutManager(context)
        favoriteAdapter =
            FavoriteAdapter() /*{ cardModel: CardModel ->
                deleteFromCard(
                    cardModel
                )
            }*/
        binding?.catalogClothes?.adapter = favoriteAdapter
    }

    private fun loadClothesFromCard() {

        favoriteViewModel.loadClothesFromCard.observe(viewLifecycleOwner, Observer {
            favoriteAdapter?.setList(it)
            favoriteAdapter?.notifyDataSetChanged()
        })
    }
}
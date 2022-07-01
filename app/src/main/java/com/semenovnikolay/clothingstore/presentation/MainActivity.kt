package com.semenovnikolay.clothingstore.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.databinding.ActivityMainBinding
import com.semenovnikolay.clothingstore.presentation.Tabs.AddClothes
import com.semenovnikolay.clothingstore.presentation.Tabs.Favorite
import com.semenovnikolay.clothingstore.presentation.Tabs.ShoppingCart
import com.semenovnikolay.clothingstore.presentation.viewModel.AddClothesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    // ViewModel - класс, позволяющий Activity и фрагментам сохранять необходимые им объекты живыми при повороте экрана.
    // by viewModels() - делегат, которые прячет за собой создание вьюмодели
    private val addClothesViewModel: AddClothesViewModel by viewModel() // by viewModel() способ работы с viewModel через koin (в di)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        addClothesViewModel.migration(this)

        // запуск MaterialToolbar в activity_main.xml
        setSupportActionBar(binding?.topMainMenu)

        supportFragmentManager.beginTransaction().replace(R.id.mainContent, AddClothes()).commit()

        binding?.bottomMainMenu?.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.addClothesBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, AddClothes()).commit()
                R.id.favoritesBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Favorite()).commit()
                R.id.shoppingCardBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, ShoppingCart()).commit()
            }

            return@setOnItemSelectedListener true
        }
        binding?.bottomMainMenu?.selectedItemId = R.id.addClothesBottomMainMenu
    }
}
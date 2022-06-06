package com.semenovnikolay.clothingstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.semenovnikolay.clothingstore.databinding.ActivityMainBinding
import com.semenovnikolay.clothingstore.presentation.Tabs.AddClothes
import com.semenovnikolay.clothingstore.presentation.Tabs.Favorite
import com.semenovnikolay.clothingstore.presentation.Tabs.ShoppingCart


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
package com.semenovnikolay.clothingstore.data.dataSource

import android.content.Context
import com.semenovnikolay.clothingstore.data.models.AddLocalModel

interface AddClothesApiDataSource {
    fun startMigration (context: Context)
}
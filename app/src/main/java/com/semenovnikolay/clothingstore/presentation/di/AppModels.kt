package com.semenovnikolay.clothingstore.presentation.di

import androidx.room.Room
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesApiDataSource
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesDataSource
import com.semenovnikolay.clothingstore.data.dataSourceIMPL.AddClothesApiDataSourceIMPL
import com.semenovnikolay.clothingstore.data.dataSourceIMPL.AddClothesDataSourceIMPL
import com.semenovnikolay.clothingstore.data.localDB.AddClothesDB
import com.semenovnikolay.clothingstore.data.repository.AddClothesRepository
import com.semenovnikolay.clothingstore.domain.repository.AddClothesCall
import com.semenovnikolay.clothingstore.domain.useCase.AddClothesUseCase
import com.semenovnikolay.clothingstore.presentation.viewModel.AddClothesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val add = module{

    single {
        Room.databaseBuilder(androidContext(), AddClothesDB::class.java,
            "dbC").build()
    }

    single { get<AddClothesDB>().addClothesDAO }


    single<AddClothesDataSource> {
        AddClothesDataSourceIMPL(
            get()
        )
    }

    single<AddClothesApiDataSource> {
        AddClothesApiDataSourceIMPL(
            get()
        )
    }

    single<AddClothesCall> { AddClothesRepository(get(),get(), get()) }

    single { AddClothesUseCase(get()) }

    viewModel { AddClothesViewModel(get()) }

}
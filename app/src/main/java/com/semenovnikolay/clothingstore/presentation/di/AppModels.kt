package com.semenovnikolay.clothingstore.presentation.di

import androidx.room.Room
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesApiDataSource
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesDataSource
import com.semenovnikolay.clothingstore.data.dataSourceIMPL.AddClothesApiDataSourceIMPL
import com.semenovnikolay.clothingstore.data.dataSourceIMPL.AddClothesDataSourceIMPL
import com.semenovnikolay.clothingstore.data.localDB.AddClothesLocalDB
import com.semenovnikolay.clothingstore.data.repository.AddClothesRepository
import com.semenovnikolay.clothingstore.domain.repository.AddClothesCall
import com.semenovnikolay.clothingstore.domain.useCase.AddClothesUseCase
import com.semenovnikolay.clothingstore.presentation.viewModel.AddClothesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val add = module{

    single {
        Room.databaseBuilder(androidContext(), AddClothesLocalDB::class.java,
            "dbC").build()
    }

    single { get<AddClothesLocalDB>().addClothesDAO }


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

    single<AddClothesCall> { AddClothesRepository(get(),get()) }

    single { AddClothesUseCase(get()) }

    viewModel { AddClothesViewModel(get()) }

}
package com.semenovnikolay.clothingstore.presentation.di

import androidx.room.Room
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesApiDataSource
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesDataSource
import com.semenovnikolay.clothingstore.data.dataSourceIMPL.AddClothesApiDataSourceIMPL
import com.semenovnikolay.clothingstore.data.dataSourceIMPL.AddClothesDataSourceIMPL
import com.semenovnikolay.clothingstore.data.localDB.ClothesLDB
import com.semenovnikolay.clothingstore.data.repository.AddClothesRepository
import com.semenovnikolay.clothingstore.data.repository.CardRepository
import com.semenovnikolay.clothingstore.data.repository.FavoriteRepository
import com.semenovnikolay.clothingstore.data.repository.OrderApiRepository
import com.semenovnikolay.clothingstore.domain.repository.AddClothesCall
import com.semenovnikolay.clothingstore.domain.repository.CardCall
import com.semenovnikolay.clothingstore.domain.repository.FavoriteCall
import com.semenovnikolay.clothingstore.domain.repository.OrderApiCall
import com.semenovnikolay.clothingstore.domain.useCase.AddClothesUseCase
import com.semenovnikolay.clothingstore.domain.useCase.CardUseCase
import com.semenovnikolay.clothingstore.domain.useCase.FavoriteUseCase
import com.semenovnikolay.clothingstore.domain.useCase.OrderApiUseCase
import com.semenovnikolay.clothingstore.presentation.viewModel.AddClothesViewModel
import com.semenovnikolay.clothingstore.presentation.viewModel.CardViewModel
import com.semenovnikolay.clothingstore.presentation.viewModel.FavoriteViewModel
import com.semenovnikolay.clothingstore.presentation.viewModel.OrderApiViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val add = module{

    single {
        Room.databaseBuilder(androidContext(), ClothesLDB::class.java,
            "dbC").build()
    }

    single { get<ClothesLDB>().addClothesDAO }


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

val card = module{

    single {
        Room.databaseBuilder(androidContext(), ClothesLDB::class.java,
            "dbO").build()
    }

    single { get<ClothesLDB>().cardDao }

    single<CardCall> { CardRepository(get()) }

    single { CardUseCase(get()) }

    viewModel { CardViewModel(get()) }

}

val favorite = module{

    single {
        Room.databaseBuilder(androidContext(), ClothesLDB::class.java,
            "dbO").build()
    }

    single { get<ClothesLDB>().favoriteDAO }

    single<FavoriteCall> { FavoriteRepository(get()) }

    single { FavoriteUseCase(get()) }

    viewModel { FavoriteViewModel(get()) }

}

val orderApi= module{

    single<OrderApiCall> { OrderApiRepository() }

    single { OrderApiUseCase(get()) }

    viewModel { OrderApiViewModel(get()) }

}
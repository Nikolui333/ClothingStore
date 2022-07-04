package com.semenovnikolay.clothingstore.data.dataSourceIMPL

import android.content.Context
import android.widget.Toast
import com.semenovnikolay.clothingstore.data.api.ApiClient
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesApiDataSource
import com.semenovnikolay.clothingstore.data.dataSource.AddClothesDataSource
import com.semenovnikolay.clothingstore.data.models.AddApiModel
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddClothesApiDataSourceIMPL(private val addClothesDataSource: AddClothesDataSource) : AddClothesApiDataSource {

    override fun startMigration(context: Context) {
        // вызываем ApiClient в котором содержится ссылка для получения данных
        val call = ApiClient.instance?.api?.loadMedicinesApi()
        call?.enqueue(object: Callback<ArrayList<AddApiModel>> { // MedicationsApiModel идентична таблице базы данных на сервере
            override fun onResponse(
                call: Call<ArrayList<AddApiModel>>,
                response: Response<ArrayList<AddApiModel>>
            ) {
                // создаём список
                var loadAddClothes: ArrayList<AddApiModel>? = null
                // очищаем массив
                loadAddClothes?.clear()
                // получаем данные с сервера
                loadAddClothes = (response.body() as ArrayList<AddApiModel>?)!!

                // помещение данных в локальную базу данных
                for (audit in loadAddClothes) {

                    audit.id?.let {
                        AddLocalModel( // MedicationsModel - локальная таблица базы данных
                            it,
                            audit.name.toString(),
                            audit.image.toString(),
                            audit.description.toString(),
                            audit.discount.toString(),
                            audit.price.toString(),
                            "40"
                        )
                    }?.let {
                        addClothesDataSource.insert(
                            it
                        )
                    }

                }

                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ArrayList<AddApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

            }
        })

    }
}
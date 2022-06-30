package com.semenovnikolay.clothingstore.data.api

import com.semenovnikolay.clothingstore.data.models.AddApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("loadClothes.php")
    fun loadMedicinesApi(): Call<ArrayList<AddApiModel>>

/*    @FormUrlEncoded
    @POST("insert.php")
    fun insert(
        @Field("name") name: String?,
        @Field("phone") phone: String?,
        @Field("description") description: String?,
        @Field("priceOrder") priceOrder: String?,
    ): Call<ResponseBody?>?*/
}
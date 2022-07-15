package com.semenovnikolay.clothingstore.data.repository

import android.content.Context
import com.semenovnikolay.clothingstore.data.api.ApiClient
import com.semenovnikolay.clothingstore.domain.repository.OrderApiCall
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderApiRepository: OrderApiCall {

    override fun insert(context: Context, name:String, phone:String, description:String, priceOrder:String) {

        val callInsertCategory: Call<ResponseBody?>? = ApiClient.instance?.api?.insert(name, phone, description, priceOrder)
        callInsertCategory?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                // если данные будут переданны неудачно, приложение попробует передать их ещё раз
                insert(context, name, phone, description, priceOrder)
            }
        })

    }
}
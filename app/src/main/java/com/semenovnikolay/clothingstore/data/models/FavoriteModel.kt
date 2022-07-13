package com.semenovnikolay.clothingstore.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_data_table")
class FavoriteModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_id")
    val id:Int,

    @ColumnInfo(name = "favorite_name")
    val name:String,

    @ColumnInfo(name = "favorite_image")
    val image:String,

    @ColumnInfo(name = "favorite_price")
    val price:String,

    // id товара, которое регулирует то, добавлен товар в избранное или нет
    @ColumnInfo(name = "favorite_idProduct")
    val idFavoriteProduct:String,

    // размер одежды
    @ColumnInfo(name = "favorite_size")
    val size:String
)
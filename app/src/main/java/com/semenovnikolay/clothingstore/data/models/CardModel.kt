package com.semenovnikolay.clothingstore.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_data_table")
class CardModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id")
    val id:Int,

    @ColumnInfo(name = "card_name")
    val name:String,

    @ColumnInfo(name = "card_image")
    val image:String,

    @ColumnInfo(name = "card_price")
    val price:String,

    // id товара, которое регулирует то, добавлен товар в корзину или нет
    @ColumnInfo(name = "card_idProduct")
    val idProduct:String,

    // размер одежды
    @ColumnInfo(name = "card_size")
    val size:String
)
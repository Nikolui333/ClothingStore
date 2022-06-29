package com.semenovnikolay.clothingstore.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_clothes_data_table")
class AddLocalModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "add_clothes_id")
    val id:Int,

    @ColumnInfo(name = "add_clothes_name")
    val name:String,

    @ColumnInfo(name = "add_clothes_image")
    val image:String,

    @ColumnInfo(name = "add_clothes_description")
    val description:String,

    @ColumnInfo(name = "add_clothes_discount")
    val discount:String,

    @ColumnInfo(name = "add_clothes_price")
    val price:String
    )
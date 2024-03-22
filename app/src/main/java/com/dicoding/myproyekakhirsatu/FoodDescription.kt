package com.dicoding.myproyekakhirsatu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodDescription (
    val price: String,
    val descriptionLong: String,
    val composition: String,
    val photo: String
    ): Parcelable
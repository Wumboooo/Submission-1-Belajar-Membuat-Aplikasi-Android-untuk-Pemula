package com.dicoding.myproyekakhirsatu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food (
    val name: String,
    val descriptionShort: String,
    val photo: String
): Parcelable
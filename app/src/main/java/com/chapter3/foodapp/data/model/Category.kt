package com.chapter3.foodapp.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.util.UUID

data class Category(
    var id : String = UUID.randomUUID().toString(),
    @DrawableRes
    var image : Int,
    var name : String
)

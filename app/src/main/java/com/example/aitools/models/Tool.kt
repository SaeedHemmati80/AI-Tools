package com.example.aitools.models

import android.os.Parcelable
import com.example.aitools.R
import kotlinx.parcelize.Parcelize

data class Tool(
    val title: String,
    val desc: String,
    var image: Int,
    var fav: Boolean = false
){

}
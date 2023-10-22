package com.example.aitools.models

import com.example.aitools.R

//TODO Set Favorite images
data class Tool(
    val title: String,
    val desc: String,
    var image: Int,
    var fav: Boolean = false
) {

}
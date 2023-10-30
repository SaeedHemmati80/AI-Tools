package com.example.aitools.models

import androidx.room.Entity

data class Category(

    var name:String,
    val tools:MutableList<Tool> = mutableListOf()
) {
}
package com.example.aitools.models

import androidx.room.Entity

@Entity(tableName = "category_tbl")
data class Category(
    var name:String,
    val tools:MutableList<Tool> = mutableListOf()
) {
}
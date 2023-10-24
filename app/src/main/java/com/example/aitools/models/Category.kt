package com.example.aitools.models

data class Category(
    var name:String,
    val tools:MutableList<Tool> = mutableListOf()
) {
}
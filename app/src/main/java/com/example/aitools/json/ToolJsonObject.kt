package com.example.aitools.json

import com.fasterxml.jackson.annotation.JsonProperty

class ToolJsonObject{
    val image_url:String = ""
    val title:String = ""
    val description:String = ""
    val price:String = ""
    val hashtags:List<String> = mutableListOf()
    val categories:String=""
}

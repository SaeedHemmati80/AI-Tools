package com.example.aitools.json

import android.app.Application
import com.example.aitools.db.ToolDataBase
import com.example.aitools.models.Tool
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JsonUtil {
    companion object {
        private fun readJsonObjectsFromString(jsonString: String): List<ToolJsonObject> {
            val objectMapper = ObjectMapper()
            val allTools = objectMapper.readValue<List<ToolJsonObject>>(
                jsonString,
                object : TypeReference<List<ToolJsonObject?>?>() {})
            return allTools
        }

        suspend fun insertToolJsonToDb(allTools:MutableList<Tool>, jsonFileAddress:String, database: ToolDataBase, application: Application) {
            val jsonString = convertJsonToString(jsonFileAddress, application)
            println(jsonString)
            val jsonObjects: List<ToolJsonObject> = readJsonObjectsFromString(jsonString)
            for (selected in jsonObjects) {
                val tool = Tool(
                    0,
                    selected.title,
                    selected.description,
                    selected.image_url,
                    false,
                    selected.categories
                )
                allTools.add(tool)
                database.toolDao.insertTool(tool)

            }
        }

        private fun convertJsonToString(jsonFileAddress: String, application: Application): String {
            return application.assets.open(jsonFileAddress).bufferedReader().use { it.readText() }
        }
    }
}
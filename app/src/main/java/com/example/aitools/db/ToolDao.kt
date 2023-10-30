package com.example.aitools.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aitools.models.Tool

@Dao
interface ToolDao {

    @Insert
    suspend fun insertTool(tool: Tool)

    @Update
    suspend fun updateTool(tool: Tool)

    @Delete
    suspend fun deleteTool(tool: Tool)

    @Query("SELECT * FROM tool_tbl")
    suspend fun getAllTool(): MutableList<Tool>

    @Query("SELECT DISTINCT tool_category From tool_tbl")
    suspend fun getCategories():MutableList<String>

    @Query("SELECT * FROM tool_tbl WHERE tool_category=:categoryName")
    suspend fun getCategoryTools(categoryName:String):MutableList<Tool>

    @Query("UPDATE tool_tbl SET tool_isFavorite = :status WHERE tool_id = :toolID")
    suspend fun changeFavoriteStatus(toolID:Int,status:Boolean)

    @Query("SELECT * FROM tool_tbl WHERE tool_isFavorite = 1 ")
    suspend fun getFavoriteItems():MutableList<Tool>
}
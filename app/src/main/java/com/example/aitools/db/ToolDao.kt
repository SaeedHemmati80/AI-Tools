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
    suspend fun getAllTool(): List<Tool>
}
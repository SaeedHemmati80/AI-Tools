package com.example.aitools.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aitools.models.Tool

@Database(entities = [Tool::class,], version = 1)
abstract class ToolDataBase : RoomDatabase() {
    abstract val toolDao: ToolDao

    companion object{
        private var INSTANCE: ToolDataBase? = null

        fun getInstance(context: Context): ToolDataBase {
            synchronized(this){
                var instance: ToolDataBase? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToolDataBase::class.java,
                        "tool_database"
                    ).build()
                }
                return instance
            }
        }
    }
}
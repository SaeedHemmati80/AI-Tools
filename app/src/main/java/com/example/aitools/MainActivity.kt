package com.example.aitools

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityMainBinding
import com.example.aitools.db.ToolDataBase
import com.example.aitools.json.ToolJsonObject
import com.example.aitools.models.Category
import com.example.aitools.models.Tool
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ToolsAdapter
    private var allTools:MutableList<Tool> = mutableListOf()
    private lateinit var database:ToolDataBase



    @RequiresApi(34)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database  = ToolDataBase.getInstance(this)


        //Get All Tools
        CoroutineScope(Dispatchers.IO).launch {
            allTools.addAll(readAllData())
        }
        Log.d("ALL TOOLS SIZE:", "onCreate: ${allTools.size}")
        initRecyclerViewTools()

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.categories_menu ->{
                    CategoryActivity.mainAdapter = adapter
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    true

                }
                R.id.fav_menu->{
                    FavActivity.favorites = this.allTools.filter { it.fav }.toMutableList()
                    FavActivity.mainToolsAdapter = adapter
                    val intent = Intent(this, FavActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

    }

    private suspend fun readAllData():MutableList<Tool>{
        return database.toolDao.getAllTool();
    }

    private fun initRecyclerViewTools(){
        binding.rvTools.layoutManager = GridLayoutManager(this, 2)
        adapter = ToolsAdapter(this,allTools ){ itemTool: Tool -> itemClickListener(itemTool)}
        binding.rvTools.adapter = adapter
    }

    private fun itemClickListener(tool: Tool){
        //Toast.makeText(this, tool.title, Toast.LENGTH_SHORT).show()

        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("Title", tool.title)
        intent.putExtra("Desc", tool.desc)
        intent.putExtra("Image", tool.image)
        startActivity(intent)

    }



}




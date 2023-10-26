package com.example.aitools

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityMainBinding
import com.example.aitools.json.ToolJsonObject
import com.example.aitools.models.Category
import com.example.aitools.models.Tool
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoriesMain:MutableList<Category>


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        categoriesMain = mutableListOf()

        //LETS READ JSON FROM STRING===================
        val fileName = "tool_data1.json";
        val allTools = readJsonObjectsFromString(fileName)
        //NOW GET UNIQUE CATS FROM all JSONS=========================
        allTools.stream().map {it.categories }
            .distinct()
            .forEach { categoriesMain.add(Category(it))}

        initRecyclerViewTools(allTools)

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.categories_menu ->{
                    CategoryActivity.categories = categoriesMain
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    true

                }
                else -> false
            }
        }

    }


    private fun readJsonObjectsFromString(fileName:String):List<ToolJsonObject>{
        val jsonString = application.assets.open(fileName).bufferedReader().use{ it.readText() }
        val objectMapper = ObjectMapper()
        val allTools = objectMapper.readValue<List<ToolJsonObject>>(
            jsonString,
            object : TypeReference<List<ToolJsonObject?>?>() {})
        return allTools
    }

    private fun initRecyclerViewTools(toolsJsonObject:List<ToolJsonObject>){
        binding.rvTools.layoutManager = GridLayoutManager(this, 2)
        binding.rvTools.adapter = ToolsAdapter(this, setData(categoriesMain,toolsJsonObject)){ itemTool: Tool -> itemClickListener(itemTool)}
    }

    private fun setData(categories:List<Category>,jsonObjects:List<ToolJsonObject>): List<Tool> {

        for(selected in jsonObjects){
            val tool =  Tool(selected.title,selected.description, selected.image_url,false)
            insertToolToCategory(tool,selected.categories)
        }
        val allTools:MutableList<Tool> = mutableListOf()
        for(category in categories){
            for(item in category.tools){
                allTools.add(item)
            }
        }
        return allTools
    }
    private fun insertToolToCategory(tool:Tool,category:String){
        val category = categoriesMain.filter { it->it.name.equals(category) }.first().tools.add(tool)
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




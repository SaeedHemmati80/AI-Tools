package com.example.aitools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.CategoryAdapter
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityCategoryBinding
import com.example.aitools.db.ToolDataBase
import com.example.aitools.models.Category
import com.example.aitools.models.Tool
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryActivity : AppCompatActivity() {
    private var categories:MutableList<Category> = mutableListOf()
    private lateinit var db:ToolDataBase
    private lateinit var binding: ActivityCategoryBinding

    companion object{
        @JvmStatic
        lateinit var mainAdapter:ToolsAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = ToolDataBase.getInstance(this)
        CoroutineScope(Dispatchers.IO).launch {
            readAllCategories()
        }
        initRecyclerViewCategory()
    }

    private suspend fun readAllCategories(){
        db.toolDao.getCategories()
            .stream()
            .map { it->Category(it) }
            .forEach {categories.add(it)}
    }

    private fun initRecyclerViewCategory(){
        binding.rvCategories.layoutManager = GridLayoutManager(this, 2)
        binding.rvCategories.adapter = CategoryAdapter(categories,db){
            val intent = Intent(this@CategoryActivity, CategoryDetailsActivity::class.java)
            startActivity(intent)
        }
    }

}
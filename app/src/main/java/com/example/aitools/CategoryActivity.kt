package com.example.aitools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.CategoryAdapter
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityCategoryBinding
import com.example.aitools.models.Category
import com.example.aitools.models.Tool

class CategoryActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        lateinit var categories:List<Category>
        lateinit var mainAdapter:ToolsAdapter

    }
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerViewCategory()
    }

    private fun initRecyclerViewCategory(){
        binding.rvCategories.layoutManager = GridLayoutManager(this, 2)
        binding.rvCategories.adapter = CategoryAdapter(categories){
            val intent = Intent(this@CategoryActivity, CategoryDetailsActivity::class.java)
            startActivity(intent)
        }
    }

}
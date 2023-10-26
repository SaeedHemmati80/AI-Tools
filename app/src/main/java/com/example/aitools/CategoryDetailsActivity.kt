package com.example.aitools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityCategoryDetailsBinding
import com.example.aitools.databinding.ActivityFavBinding
import com.example.aitools.databinding.ActivityMainBinding
import com.example.aitools.models.Category
import com.example.aitools.models.Tool

class CategoryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailsBinding
    companion object{
        @JvmStatic
        lateinit var category:Category
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCategoryDetailsBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCatDetails.layoutManager = GridLayoutManager(this,2)
        binding.rvCatDetails.adapter = ToolsAdapter(this, category.tools){
            item:Tool->itemClickListener(item)
        }
    }
    private fun itemClickListener(tool: Tool){

        val intent = Intent(this@CategoryDetailsActivity, DetailsActivity::class.java)
        intent.putExtra("Title", tool.title)
        intent.putExtra("Desc", tool.desc)
        intent.putExtra("Image", tool.image)
        startActivity(intent)

    }

    override fun onBackPressed() {
        CategoryActivity.mainAdapter.notifyDataSetChanged()
        super.onBackPressed()
    }
}
package com.example.aitools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityMainBinding
import com.example.aitools.models.Tool

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
         binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.rvTools.layoutManager = GridLayoutManager(this, 2)
        binding.rvTools.adapter = ToolsAdapter(setData())
    }

    private fun setData() = listOf(
        Tool("Title 1", "DescDescDescDescDescDescDescDescDescDesc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "DeDescDescDescDescDescDescDescDescDescDescDesc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
        Tool("Title 1", "Desc", R.drawable.temp_icon),
    )
}
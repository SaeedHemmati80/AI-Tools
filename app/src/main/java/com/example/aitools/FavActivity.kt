package com.example.aitools

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityFavBinding
import com.example.aitools.db.ToolDataBase
import com.example.aitools.models.Tool

class FavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavBinding
    private lateinit var adapter: ToolsAdapter
    private lateinit var database:ToolDataBase
    companion object{
        @JvmStatic
        lateinit var favorites:MutableList<Tool>
        lateinit var mainToolsAdapter: ToolsAdapter
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = ToolDataBase.getInstance(this)

        initFavoriteRecyclerView()

    }
    fun initFavoriteRecyclerView(){
        binding.rvFavTools.layoutManager = GridLayoutManager(this,2)
        adapter = ToolsAdapter(this,favorites,database){itemTool:Tool -> itemClickListener(itemTool)}
        binding.rvFavTools.adapter = adapter
    }

    private fun itemClickListener(tool: Tool){

        val intent = Intent(this@FavActivity, DetailsActivity::class.java)
        intent.putExtra("Title", tool.title)
        intent.putExtra("Desc", tool.desc)
        intent.putExtra("Image", tool.image)
        startActivity(intent)

    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NotifyDataSetChanged")
    override fun onBackPressed() {
        mainToolsAdapter.notifyDataSetChanged()
        super.onBackPressed()
    }

}
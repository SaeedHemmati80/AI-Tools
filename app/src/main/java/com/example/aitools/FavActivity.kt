package com.example.aitools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.adapter.FavAdapter
import com.example.aitools.databinding.ActivityFavBinding
import com.example.aitools.databinding.ActivityMainBinding
import com.example.aitools.models.Tool

class FavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavBinding

    companion object{
        @JvmStatic
        lateinit var favorites:MutableList<Tool>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initFavoriteRecyclerView()

    }
    fun initFavoriteRecyclerView(){
        binding.rvFavTools.layoutManager = GridLayoutManager(this,2)
        binding.rvFavTools.adapter = FavAdapter(favorites){itemTool:Tool -> itemClickListener(itemTool)}
    }

    private fun itemClickListener(tool: Tool){
        //Toast.makeText(this, tool.title, Toast.LENGTH_SHORT).show()

        val intent = Intent(this@FavActivity, DetailsActivity::class.java)
        intent.putExtra("Title", tool.title)
        intent.putExtra("Desc", tool.desc)
        intent.putExtra("Image", tool.image)
        startActivity(intent)

    }

}
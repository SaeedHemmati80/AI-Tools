package com.example.aitools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aitools.adapter.ToolsAdapter
import com.example.aitools.databinding.ActivityMainBinding
import com.example.aitools.models.Category
import com.example.aitools.models.Tool

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var voiceAi:Category
    private lateinit var sportAi:Category
    private lateinit var pictureAi:Category
    private lateinit var categoriesMain:List<Category>


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        voiceAi = Category("Voice")
        sportAi = Category("Sport")
        pictureAi = Category("Picture")

        categoriesMain = mutableListOf(voiceAi,sportAi,pictureAi,
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),
            Category("X000"),

            )

        initRecyclerViewTools()

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



    private fun initRecyclerViewTools(){
        binding.rvTools.layoutManager = GridLayoutManager(this, 2)
        binding.rvTools.adapter = ToolsAdapter(this, setData(categoriesMain)){ itemTool: Tool -> itemClickListener(itemTool)}
    }

    private fun setData(categories:List<Category>): List<Tool> {

        categories[0].tools.addAll(mutableListOf(
            Tool("Title 1", "DescDescDescDescDescDescDescDescDescDesc", R.drawable.temp_icon),
            Tool("Title 2", "Desc", R.drawable.wat),
            Tool("Title 3", "Desc", R.drawable.temp_icon)
        ))

        categories[1].tools.addAll(mutableListOf(
            Tool("Title 4", "Desc", R.drawable.temp_icon),
            Tool("Title 5", "DeDescDescDescDescDescDescDescDescDescDescDesc", R.drawable.insta),
            Tool("Title 1", "Desc", R.drawable.temp_icon),
            Tool("Title 1", "Desc", R.drawable.temp_icon),
        ))

        categories[2].tools.addAll(mutableListOf(
            Tool("Title 1", "Desc", R.drawable.insta),
            Tool("Title 1", "Desc", R.drawable.wat),
            Tool("Title 1", "Desc", R.drawable.temp_icon),
            Tool("Title 1", "Desc", R.drawable.temp_icon),
            Tool("Title 1", "Desc", R.drawable.temp_icon),
            Tool("Title 1", "Desc", R.drawable.temp_icon),
        ))

        return categories[0].tools + categories[1].tools+categories[2].tools
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




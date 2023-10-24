package com.example.aitools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aitools.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle: Bundle? = intent.extras

        val title = bundle?.getString("Title")
        val desc = bundle?.getString("Desc")
        val imgId = bundle?.getInt("Image")


        binding.tvTitleDetails.text = title
        binding.tvDescDetails.text = desc
        binding.imgDetails.setImageResource(imgId!!)

    }
}
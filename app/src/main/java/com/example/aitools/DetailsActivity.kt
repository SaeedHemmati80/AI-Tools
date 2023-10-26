package com.example.aitools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aitools.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle: Bundle? = intent.extras

        val title = bundle?.getString("Title")
        val desc = bundle?.getString("Desc")
        val imgId = bundle?.getString("Image")

        binding.apply {
            tvTitleDetails.text = title
            tvDescDetails.text = desc
            Picasso.get().load(imgId).into(imgDetails)
        }


    }
}
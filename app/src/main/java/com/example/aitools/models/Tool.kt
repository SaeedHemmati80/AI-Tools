package com.example.aitools.models

import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize

//@Parcelize
data class Tool(
    val title: String,
    val desc: String,
    var image: String,
    var fav: Boolean = false
){
    companion object {
        @JvmStatic
        @BindingAdapter("android:loadURLImageView")
        fun loadURLImageView(imageView: ImageView, url_img: String) {
            Picasso.get().load(url_img).into(imageView)
        }
    }

}
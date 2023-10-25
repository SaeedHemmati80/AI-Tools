package com.example.aitools.models

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize

@Parcelize
class Tool(
    val title: String,
    val desc: String,
    var image: String,
    var fav: Boolean = false
) : Parcelable {
    companion object {
        @JvmStatic
        @BindingAdapter("android:loadURLImageView")
        fun loadURLImageView(imageView: ImageView, url_img: String) {
            Picasso.get().load(url_img).into(imageView)
        }
    }

}
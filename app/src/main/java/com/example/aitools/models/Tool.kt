package com.example.aitools.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.picasso.Picasso

@Entity(tableName = "tool_tbl")
data class Tool(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="tool_id", defaultValue = "0")
    val id:Int,
    @ColumnInfo(name="tool_title")
    val title: String,
    @ColumnInfo(name="tool_desc")
    val desc: String,
    @ColumnInfo(name="tool_image")
    var image: String,
    @ColumnInfo(name="tool_isFavorite")
    var fav: Boolean = false,
    @ColumnInfo(name="tool_category")
    var categroy:String
){
    companion object {
        @JvmStatic
        @BindingAdapter("android:loadURLImageView")
        fun loadURLImageView(imageView: ImageView, url_img: String) {
            Picasso.get().load(url_img).into(imageView)
        }
    }

}
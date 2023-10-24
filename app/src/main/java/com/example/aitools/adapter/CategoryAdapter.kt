package com.example.aitools.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.R
import com.example.aitools.models.Category

class CategoryAdapter(private val lstCategory:List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstCategory[position])
    }

    override fun getItemCount(): Int {
        return lstCategory.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val catName = itemView.findViewById<TextView>(R.id.tv_cat_name)
        fun bind(cat: Category){
            catName.text = cat.name
        }
    }
}
package com.example.aitools.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.CategoryDetailsActivity
import com.example.aitools.R
import com.example.aitools.db.ToolDataBase
import com.example.aitools.models.Category
import com.example.aitools.models.Tool
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryAdapter(
    private val lstCategory:List<Category>,
    private val dataBase: ToolDataBase,
    private val clickListener: (cat: Category) -> Unit):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstCategory[position],clickListener)
    }

    override fun getItemCount(): Int {
        return lstCategory.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val catName = itemView.findViewById<TextView>(R.id.tv_cat_name)
        val cardCat = itemView.findViewById<CardView>(R.id.card_cat)
        fun bind(cat: Category,clickListener: (cat: Category) -> Unit){
            catName.text = cat.name

            cardCat.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    CategoryDetailsActivity.category = cat
                    cat.tools.addAll(readCategoryTools(cat.name))
                }
                clickListener(cat)
            }
        }
        suspend fun readCategoryTools(categoryName:String):MutableList<Tool>{
            return dataBase.toolDao.getCategoryTools(categoryName)
        }
    }
}
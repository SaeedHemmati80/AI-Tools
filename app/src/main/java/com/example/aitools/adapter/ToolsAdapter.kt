package com.example.aitools.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.R
import com.example.aitools.models.Tool

class ToolsAdapter(private val lstTools: List<Tool>): RecyclerView.Adapter<ToolsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tool_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToolsAdapter.ViewHolder, position: Int) {
        holder.bind(lstTools[position])
        holder.favImg.setOnClickListener {
            if(! lstTools[position].fav){
                holder.favImg.setImageResource(R.drawable.star_filled)
            }else{
                holder.favImg.setImageResource(R.drawable.star_empty)
            }
            lstTools[position].fav = !lstTools[position].fav
        }
    }

    override fun getItemCount(): Int {
        return lstTools.size
    }

    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
         val title: TextView = itemView.findViewById(R.id.tv_title)
         val desc: TextView = itemView.findViewById(R.id.tv_desc)
         val img: ImageView = itemView.findViewById(R.id.img_tools)
         val favImg: ImageButton = itemView.findViewById(R.id.img_star)

        fun bind(tool: Tool){
            title.text = tool.title
            desc.text = tool.desc
            img.setImageResource(tool.image)
            if(tool.fav){
                favImg.setImageResource(R.drawable.star_filled)
            }else
                favImg.setImageResource(R.drawable.star_empty)
        }

    }
}
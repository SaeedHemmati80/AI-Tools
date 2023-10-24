package com.example.aitools.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.R
import com.example.aitools.models.Tool

class ToolsAdapter(
    private val context: Context,
    private val lstTools: List<Tool>,
    private val clickListener: (tool: Tool) -> Unit
) : RecyclerView.Adapter<ToolsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tool_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToolsAdapter.ViewHolder, position: Int) {
        holder.bind(lstTools[position], clickListener)

        // OnClick for star
        holder.favImg.setOnClickListener {
            if (!lstTools[position].fav) {
                holder.favImg.setImageResource(R.drawable.star_filled)
            } else {
                holder.favImg.setImageResource(R.drawable.star_empty)
            }
            lstTools[position].fav = !lstTools[position].fav
        }


    }

    override fun getItemCount(): Int {
        return lstTools.size
    }

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val desc: TextView = itemView.findViewById(R.id.tv_desc)
        val img: ImageView = itemView.findViewById(R.id.img_tools)
        val favImg: ImageButton = itemView.findViewById(R.id.img_star)
        val cardTool: CardView = itemView.findViewById(R.id.cardTool)

        fun bind(tool: Tool, clickListener: (tool: Tool) -> Unit) {
            title.text = tool.title
            desc.text = tool.desc
            img.setImageResource(tool.image)
            if (tool.fav) {
                favImg.setImageResource(R.drawable.star_filled)
            } else
                favImg.setImageResource(R.drawable.star_empty)

            // OnClick for view
            cardTool.setOnClickListener {
                clickListener(tool)
            }
        }

    }
}
package com.example.aitools.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.R
import com.example.aitools.databinding.ToolItemBinding
import com.example.aitools.models.Tool

class FavAdapter(
    private var lstFavorites:MutableList<Tool>,
    private val clickListener: (tool: Tool) -> Unit):
    RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ToolItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.tool_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lstFavorites.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstFavorites[position], clickListener)
        // OnClick for star
        holder.favImg.setOnClickListener {
            if (!lstFavorites[position].fav) {
                holder.favImg.setImageResource(R.drawable.star_filled)
            } else {
                holder.favImg.setImageResource(R.drawable.star_empty)
            }
            lstFavorites[position].fav = !lstFavorites[position].fav
            lstFavorites = lstFavorites.filter { it.fav }.toMutableList()
            notifyDataSetChanged()

        }
    }
    class ViewHolder(private val binding: ToolItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        val favImg: ImageButton = itemView.findViewById(R.id.img_star)
        val imgTool: ImageView = itemView.findViewById(R.id.img_tools)
        fun bind(tool: Tool, clickListener: (tool: Tool) -> Unit) {
            binding.apply {
                myTool = tool
                if (tool.fav) {
                    imgStar.setImageResource(R.drawable.star_filled)
                } else
                    imgStar.setImageResource(R.drawable.star_empty)

                // OnClick for view
                cardTool.setOnClickListener {
                    clickListener(tool)
                }
            }
        }
    }
}

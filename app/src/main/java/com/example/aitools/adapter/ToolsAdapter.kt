package com.example.aitools.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.R
import com.example.aitools.databinding.ToolItemBinding
import com.example.aitools.db.ToolDataBase
import com.example.aitools.models.Tool
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToolsAdapter(
    private val context: Context,
    private var lstTools: List<Tool>,
    private val database:ToolDataBase,
    private val clickListener: (tool: Tool) -> Unit
) : RecyclerView.Adapter<ToolsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ToolItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.tool_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstTools[position], clickListener)
        // OnClick for star
        holder.favImg.setOnClickListener {
            if (!lstTools[position].fav) {
                holder.favImg.setImageResource(R.drawable.star_filled)
            } else {
                holder.favImg.setImageResource(R.drawable.star_empty)
            }
            lstTools[position].fav = !lstTools[position].fav
            CoroutineScope(Dispatchers.IO).launch {
                database.toolDao.changeFavoriteStatus(lstTools[position].id,lstTools[position].fav)
            }
        }
    }

    override fun getItemCount(): Int {
        return lstTools.size
    }

    fun filterList(filteredList: MutableList<Tool>) {
        lstTools = filteredList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ToolItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val favImg: ImageButton = itemView.findViewById(R.id.img_star)
        val img_tool: ImageView = itemView.findViewById(R.id.img_tools)
        fun bind(tool: Tool, clickListener: (tool: Tool) -> Unit) {
            binding.apply {
                myTool = tool
                if (tool.fav) {
                    imgStar.setImageResource(R.drawable.star_filled)
                } else {
                    imgStar.setImageResource(R.drawable.star_empty)
                }

                // OnClick for view
                cardTool.setOnClickListener {
                    clickListener(tool)
                }
            }
        }
    }
}
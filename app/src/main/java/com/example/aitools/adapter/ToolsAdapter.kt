package com.example.aitools.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aitools.R
import com.example.aitools.databinding.ToolItemBinding
import com.example.aitools.models.Tool

class ToolsAdapter(
    private val context: Context,
    private val lstTools: List<Tool>,
    private val clickListener: (tool: Tool) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
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
        }
    }

    override fun getItemCount(): Int {
        return lstTools.size
    }
}
class ViewHolder(private val binding: ToolItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val favImg: ImageButton = itemView.findViewById(R.id.img_star)
    fun bind(tool: Tool, clickListener: (tool: Tool) -> Unit) {
        binding.apply {
//            tvTitle.text = tool.title
//            tvDesc.text = tool.desc
//            imgTools.setImageResource(tool.image)
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


//        val title: TextView = itemView.findViewById(R.id.tv_title)
//        val desc: TextView = itemView.findViewById(R.id.tv_desc)
//        val img: ImageView = itemView.findViewById(R.id.img_tools)
//        val favImg: ImageButton = itemView.findViewById(R.id.img_star)
//        val cardTool: CardView = itemView.findViewById(R.id.cardTool)
//
//        fun bind(tool: Tool, clickListener: (tool: Tool) -> Unit) {
//            title.text = tool.title
//            desc.text = tool.desc
//            img.setImageResource(tool.image)
//            if (tool.fav) {
//                favImg.setImageResource(R.drawable.star_filled)
//            } else
//                favImg.setImageResource(R.drawable.star_empty)
//
//            // OnClick for view
//            cardTool.setOnClickListener {
//                clickListener(tool)
//            }
//        }

}
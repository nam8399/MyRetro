package com.sikstree.myretro.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sikstree.myretro.Data.ItemData
import com.sikstree.myretro.R

class ListAdapterGrid(var list: ArrayList<String>): RecyclerView.Adapter<ListAdapterGrid.ViewHolder>() {

    var datas = mutableListOf<ItemData>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val itemTitle: TextView = itemView.findViewById(R.id.item_title)
        private val itemUrl: String = ""
        private val itemImg: ImageView = itemView.findViewById(R.id.item_img)

        fun bind(item: ItemData) {
            itemTitle.text = item.title
//            itemUrl = item.age.toString()
            Glide.with(itemView).load(item.img).into(itemImg)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_grid_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])


//        holder.layout.layoutListItem.setOnClickListener {
//            Toast.makeText(holder.layout.context, "${list[position]} Click!", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

package com.example.bonusassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.names_item_row.view.*

class NamesRecyclerViewAdapter(private val names: MutableList<String>): RecyclerView.Adapter<NamesRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.names_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name = names[position]

        holder.itemView.apply {
            item_row_name.text = "$name"
            item_row_id. text = "$position"
        }
    }

    override fun getItemCount(): Int = names.size
}
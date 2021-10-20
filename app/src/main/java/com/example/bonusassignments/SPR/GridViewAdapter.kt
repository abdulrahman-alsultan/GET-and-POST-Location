package com.example.bonusassignments.SPR

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.bonusassignments.R
import kotlinx.android.synthetic.main.simple_post_request_grid_view.view.*

class GridViewAdapter(private val names: List<MyDataItem>, private val ctx: Context): BaseAdapter() {
    override fun getCount(): Int = names.size

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val name = names[p0]

        val inflater = ctx?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val appView = inflater.inflate(R.layout.simple_post_request_grid_view, null)

        appView.simple_post_request_grid_view_tv_name.text = name.name

        return appView
    }
}
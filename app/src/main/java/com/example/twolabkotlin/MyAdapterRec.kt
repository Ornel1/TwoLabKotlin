package com.example.twolabkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapterRec: RecyclerView.Adapter<MyAdapterRec.MyHolder>() {

    var list = listOf<CitysAndTemp>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyHolder(view: View): RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.cityView)
        val tempName: TextView = view.findViewById(R.id.tempView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = list[position]
        holder.cityName.text = item.city
        holder.tempName.text = item.temp

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
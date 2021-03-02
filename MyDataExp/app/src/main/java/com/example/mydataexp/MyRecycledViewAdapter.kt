package com.example.mydataexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import java.util.ArrayList
import com.example.mydataexp.mydata.User

class MyRecycledViewAdapter(private val tags: ArrayList<User>, private val onClickListener: MyOnClickListener) : RecyclerView.Adapter<MyRecycledViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tagTV: TextView = view.findViewById<View>(R.id.tv_user) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycled_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tag = tags[position]
        holder.tagTV.text = tag.firstName

        Log.d("RV","Hi")
        holder.tagTV.setOnClickListener {
            onClickListener.onClicked(tag.uid as Int)
        }
    }


    override fun getItemCount(): Int {
        return tags.size
    }

}
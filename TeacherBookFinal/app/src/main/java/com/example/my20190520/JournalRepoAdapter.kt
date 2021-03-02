package com.example.my20190520


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.util.Log

import com.example.my20190520.Local.Entity.JournalView

class JournalRepoAdapter : RecyclerView.Adapter<JournalRepoAdapter.ViewHolder>() {
    private val focusedItem = 0
    var data = mutableListOf<JournalView>()
    public var title = fun(uid:Int) {Log.d("1","11")}

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {


        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.rv_layout, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        try {
            p0.itemView.findViewById<TextView>(R.id.repoName).text = data[p1].Name
            p0.itemView.findViewById<TextView>(R.id.repoName).setOnClickListener {
                title(data[p1].uid)
                //Log.d("Hi","123");
            }
        }catch(ex:Exception){
            Log.d("Hi",ex.message);
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
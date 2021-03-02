package com.example.my20190520


import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.util.Log

import com.example.my20190520.Local.Entity.Subject

class SubjectRepoAdapter : RecyclerView.Adapter<SubjectRepoAdapter.ViewHolder>() {
    private val focusedItem = 0
    var data = mutableListOf<Subject>()
    public var title = fun() {Log.d("1","11")}

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
                title()
                //Log.d("Hi","123");
            }
        }catch(ex:Exception){
            Log.d("Hi",ex.message);
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            //super(itemView)

            // Handle item click and set the selection
            itemView.setOnClickListener {
                // Redraw the old selection and the new
                /*notifyItemChanged(focusedItem)
                focusedItem = layoutPosition
                notifyItemChanged(focusedItem)*/
                //val intent = Intent(this, Subjects::class.java)
                // start your next activity
                //startActivity(intent)

                Log.d("Hello",layoutPosition.toString());
            }
        }
    }
}
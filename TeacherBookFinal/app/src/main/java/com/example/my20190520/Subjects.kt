package com.example.my20190520
import android.util.Log
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.my20190520.Local.AppDatabase
import com.example.my20190520.Local.Entity.*
import com.example.my20190520.Rest.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.coroutines.*
import okhttp3.Request
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import android.widget.AdapterView.OnItemClickListener



class Subjects : AppCompatActivity(),CoroutineScope {
    val adapter = SubjectRepoAdapter()
    private val rootJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        adapter.title={
            val intent = Intent(this, Groups::class.java)
            // start your next activity
            startActivity(intent)
        }
        val repoList = findViewById<RecyclerView>(R.id.subjectList)
        repoList.layoutManager = LinearLayoutManager(this)
        repoList.adapter = adapter


        loadData()
    }

    private fun loadData() = launch {
        try {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mydbnew123"
        ).allowMainThreadQueries().build()

        adapter.data.clear()
        adapter.data.addAll(db.subjectDao().getAll())


                Log.d("Hello","Before");
                adapter.notifyDataSetChanged()
                Log.d("Hello","After");
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message);
            }
        }

}

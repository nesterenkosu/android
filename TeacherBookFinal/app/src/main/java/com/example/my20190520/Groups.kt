package com.example.my20190520

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.my20190520.Local.AppDatabase
import com.example.my20190520.Local.Entity.Group1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class Groups : AppCompatActivity() , CoroutineScope {
    val adapter = GroupRepoAdapter()
    private val rootJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        adapter.title={
            val intent = Intent(this, Students::class.java)
            // start your next activity
            startActivity(intent)
        }
        adapter.data= mutableListOf<Group1>()
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
        adapter.data.addAll(db.groupDao().getAll())


            Log.d("Hello","Before");
            adapter.notifyDataSetChanged()
            Log.d("Hello","After");
        }catch (ex: Exception)
        {
            Log.d("Hello",ex.message);
        }
    }

}

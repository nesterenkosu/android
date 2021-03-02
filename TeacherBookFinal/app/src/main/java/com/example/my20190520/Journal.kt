package com.example.my20190520

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.my20190520.Local.AppDatabase
import com.example.my20190520.Local.Entity.JournalView
import com.example.my20190520.Local.Entity.Journal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class Journal : AppCompatActivity(), CoroutineScope {
    val adapter = JournalRepoAdapter()
    private val rootJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        adapter.title={
            AcceptLab(it)
        }
        adapter.data= mutableListOf<JournalView>()
        val repoList = findViewById<RecyclerView>(R.id.subjectList)
        repoList.layoutManager = LinearLayoutManager(this)
        repoList.adapter = adapter


        loadData()
    }

    private fun loadData() = launch {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mydbnew123"
        ).allowMainThreadQueries().build()

        //db.journalDao().deleteAll()
        adapter.data.clear()
        adapter.data.addAll(db.journalDao().getAll())

        try {
            Log.d("Hello","Before");
            adapter.notifyDataSetChanged()
            Log.d("Hello","After");
        }catch (ex: Exception)
        {
            Log.d("Hello",ex.message);
        }
    }

    private fun AcceptLab(labid: Int) = launch {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mydbnew123"
        ).allowMainThreadQueries().build()

        db.journalDao().insertAll(Journal(Random.nextInt(),1,labid,1))
        loadData()
    }

}

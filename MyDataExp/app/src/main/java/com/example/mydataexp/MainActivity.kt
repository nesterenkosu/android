package com.example.mydataexp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mydataexp.mydata.AppDatabase
import com.example.mydataexp.mydata.User
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val rootJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    private lateinit var my_edittext: EditText
    private lateinit var my_button: Button
    private lateinit var my_btnread: Button
    private lateinit var my_btnupdate: Button
    private lateinit var my_btndelete: Button
    private lateinit var my_textview: TextView
    private lateinit var my_rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_edittext = findViewById(R.id.ed_newline)
        my_button = findViewById(R.id.btn_add)
        my_textview = findViewById(R.id.tv_result)
        my_btnread = findViewById(R.id.btn_read)
        my_btnupdate = findViewById(R.id.btn_update)
        my_btndelete = findViewById(R.id.btn_delete)

        val tags = ArrayList<User>()

        //Log.d("Window is opened","test")
       // Log.

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        my_rv=findViewById(R.id.rv_users)
        my_rv .layoutManager = linearLayoutManager

        val adapter = MyRecycledViewAdapter(tags, object : MyOnClickListener{
            override fun onClicked(tag: Int) {
                my_textview.setText(tag.toString())
                //Log.d("TagsListActivity", "tag = " + tag)
            }

        })
        my_rv.adapter = adapter

        my_button.setOnClickListener {
            my_textview.setText("Записываю")
            loadData()

        }

        my_btnread.setOnClickListener{
            my_textview.setText("Читаю")
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "mydbnew123"
            ).allowMainThreadQueries().build()

            var str:String = ""
            db.userDao().getAll().forEach {
                str+=it.uid.toString()+" "+it.firstName+" "+it.lastName+"\n"
                tags.add(it)
            }

            adapter.notifyDataSetChanged()
            my_textview.setText(str)
        }

        my_btnupdate.setOnClickListener {
            my_textview.setText("Обновляю")
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "mydbnew123"
            ).allowMainThreadQueries().build()

            db.userDao().update(User(3,"Tri","Surename"))
        }

        my_btndelete.setOnClickListener {
            my_textview.setText("Удаляю")
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "mydbnew123"
            ).allowMainThreadQueries().build()

            db.userDao().delete(User(3,"Tri","Surename"))
        }



    }

        private fun loadData() = launch {
            try {
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "mydbnew123"
                ).allowMainThreadQueries().build()
                //my_textview.setText(my_text)
                db.userDao().insertAll(User(uid = null, firstName= "Vasya",lastName = "Petrov"),User(uid = null, firstName= "Kolya",lastName = "Petrov"),User(uid = null, firstName= "Petya",lastName = "Petrov"))
                my_textview.setText("Кажется записали");
            }catch (ex:Exception){
                Log.d("Excepttt",ex.message.toString())
                my_textview.setText("Нет, блин. Исключение."+ex.message.toString())
            }

            //.allowMainThreadQueries()


        }

        override fun onDestroy() {
            rootJob.cancel()
            super.onDestroy()
        }
    }

package com.example.my20190520

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
//import  android.arch
import  android.arch.persistence.room.Room;
import android.content.Intent
import  android.arch.persistence.room.RoomDatabase
//import com.example.my20190520.Local.AppDatabase
//import com.example.my20190520.Local.User
import android.R.attr.name
import android.R.id
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.my20190520.Local.Entity.Group1
import com.example.my20190520.Local.Entity.Journal
import com.example.my20190520.Local.Entity.Lab
import com.example.my20190520.Local.Entity.Student
import com.example.my20190520.Local.Entity.Subject
import com.example.my20190520.Local.Entity.SubjectGroup


//import com.example.my20190520.Local.SubjectDao
import  com.example.my20190520.Rest.GroupRepo
import  com.example.my20190520.Rest.JournalRepo
import  com.example.my20190520.Rest.LabRepo
import  com.example.my20190520.Rest.StudentRepo
import  com.example.my20190520.Rest.SubjectRepo
import  com.example.my20190520.Rest.SubjectGroupRepo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

import  com.example.my20190520.Local.AppDatabase
import com.example.my20190520.Local.DAO.SubjectGroupDao

class MainActivity : AppCompatActivity(),CoroutineScope {

    private val httpClient = OkHttpClient.Builder().build()

    private val rootJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    lateinit var  btn: Button;
    lateinit var  btn2: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.mybutton);
        btn.setOnClickListener{
            /*val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "mydbnew"
            ).allowMainThreadQueries().build()*/

            /*val employee = Employee()
            employee.id = 1
            employee.name = "John Smith"
            employee.salary = 10000

            employeeDao.insert(employee) */

            //val user = User(0,"Vasya","Kotlinov")

            /*db.userDao().insertAll(user)
            db.userDao().getAll().iterator().forEach {
                Log.d("Hello",it.firstName);
            }*/
           loadData()

            //Log.d("Hello","Hello");
        };

        btn2 = findViewById(R.id.button);
        btn2.setOnClickListener{

                val intent = Intent(this, Subjects::class.java)
                // start your next activity

                startActivity(intent)


        };
    }

    private fun loadData() = launch {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mydbnew123"
        ).allowMainThreadQueries().build()

        //-------Subjects
        var request = Request.Builder()
            .url("http://h140168.s01.test-hf.su/rest/subjects")
            .build()

        var response: String = withContext(Dispatchers.IO) {
            try {
                httpClient.newCall(request).execute().body()!!.string()
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message).toString();
            }
        }

        var type = object : TypeToken<ArrayList<SubjectRepo>>() {}
        var repos = Gson().fromJson<ArrayList<SubjectRepo>>(response, type.type)


        repos.forEach {
            //Log.d("Hello",it.Name);

            try {
                db.subjectDao().insertAll(Subject(it.ID, it.Name))
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message);
            }
        }

        //-------Groups
        request = Request.Builder()
            .url("http://h140168.s01.test-hf.su/rest/groups")
            .build()

        response = withContext(Dispatchers.IO) {
            httpClient.newCall(request).execute().body()!!.string()
        }

        //Gson().fromJson<ArrayList<GroupRepo>>(response, object : TypeToken<ArrayList<GroupRepo>>() {}.type)

        Gson().fromJson<ArrayList<GroupRepo>>(response, object : TypeToken<ArrayList<GroupRepo>>() {}.type).forEach {
            //Log.d("Hello",it.Name);

            try {
                db.groupDao().insertAll(Group1(it.ID, it.Name))
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message);
            }
        }

        //-------Journal
        request = Request.Builder()
            .url("http://h140168.s01.test-hf.su/rest/journal")
            .build()

        response = withContext(Dispatchers.IO) {
            httpClient.newCall(request).execute().body()!!.string()
        }

        //Gson().fromJson<ArrayList<GroupRepo>>(response, object : TypeToken<ArrayList<GroupRepo>>() {}.type)

        Gson().fromJson<ArrayList<JournalRepo>>(response, object : TypeToken<ArrayList<JournalRepo>>() {}.type).forEach {
            //Log.d("Hello",it.Name);

            try {
                db.journalDao().insertAll(Journal(it.ID,it.StudentID,it.LabID,0))
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message);
            }
        }

        //-------Labs
        request = Request.Builder()
            .url("http://h140168.s01.test-hf.su/rest/labs")
            .build()

        response = withContext(Dispatchers.IO) {
            httpClient.newCall(request).execute().body()!!.string()
        }

        //Gson().fromJson<ArrayList<GroupRepo>>(response, object : TypeToken<ArrayList<GroupRepo>>() {}.type)

        Gson().fromJson<ArrayList<LabRepo>>(response, object : TypeToken<ArrayList<LabRepo>>() {}.type).forEach {
            //Log.d("Hello",it.Name);

            try {
                db.labDao().insertAll(Lab(it.ID,it.Name))
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message);
            }
        }

        //-------Students
        request = Request.Builder()
            .url("http://h140168.s01.test-hf.su/rest/students")
            .build()

        response = withContext(Dispatchers.IO) {
            httpClient.newCall(request).execute().body()!!.string()
        }

        //Gson().fromJson<ArrayList<GroupRepo>>(response, object : TypeToken<ArrayList<GroupRepo>>() {}.type)

        Gson().fromJson<ArrayList<StudentRepo>>(response, object : TypeToken<ArrayList<StudentRepo>>() {}.type).forEach {
            //Log.d("Hello",it.Name);

            try {
                db.studentDao().insertAll(Student(it.ID,it.GroupID, it.Name))
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message);
            }
        }

        //-------SubjectGroup
        request = Request.Builder()
            .url("http://h140168.s01.test-hf.su/rest/subjectsgroups")
            .build()

        response = withContext(Dispatchers.IO) {
            httpClient.newCall(request).execute().body()!!.string()
        }

        //Gson().fromJson<ArrayList<GroupRepo>>(response, object : TypeToken<ArrayList<GroupRepo>>() {}.type)

        Gson().fromJson<ArrayList<SubjectGroupRepo>>(response, object : TypeToken<ArrayList<SubjectGroupRepo>>() {}.type).forEach {
            //Log.d("Hello",it.Name);

            try {
                db.subjectGroupDao().insertAll(SubjectGroup(it.ID,it.SubjectID,it.GroupID))
            }catch (ex:Exception)
            {
                Log.d("Hello",ex.message);
            }
        }

        db.groupDao().getAll().iterator().forEach {
            Log.d("Hello",it.Name);
        }

        db.journalDao().getAll().iterator().forEach {
            Log.d("Hello",it.uid.toString());
        }

        db.labDao().getAll().iterator().forEach {
            Log.d("Hello",it.Name);
        }

        db.studentDao().getAll().iterator().forEach {
            Log.d("Hello",it.GroupID.toString()+" "+it.Name);
        }

        db.subjectDao().getAll().iterator().forEach {
            Log.d("Hello",it.Name);
        }

        db.subjectGroupDao().getAll().iterator().forEach {
            Log.d("Hello",it.uid.toString());
        }
    }

    override fun onDestroy() {
        rootJob.cancel()
        super.onDestroy()
    }
}

package com.example.my20190520.Local.DAO

import  android.arch.persistence.room.Dao
import  android.arch.persistence.room.Query
import  android.arch.persistence.room.Insert
import  android.arch.persistence.room.Delete

import com.example.my20190520.Local.Entity.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Insert
    fun insertAll(vararg users: Student)
}
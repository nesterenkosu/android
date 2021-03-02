package com.example.my20190520.Local.DAO

import  android.arch.persistence.room.Dao
import  android.arch.persistence.room.Query
import  android.arch.persistence.room.Insert
import  android.arch.persistence.room.Delete

import com.example.my20190520.Local.Entity.Lab

@Dao
interface LabDao {
    @Query("SELECT * FROM Lab")
    fun getAll(): List<Lab>

    @Insert
    fun insertAll(vararg users: Lab)
}
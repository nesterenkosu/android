package com.example.my20190520.Local.DAO

import  android.arch.persistence.room.Dao
import  android.arch.persistence.room.Query
import  android.arch.persistence.room.Insert
import  android.arch.persistence.room.Delete

import com.example.my20190520.Local.Entity.SubjectGroup

@Dao
interface SubjectGroupDao {
    @Query("SELECT * FROM SubjectGroup")
    fun getAll(): List<SubjectGroup>

    @Insert
    fun insertAll(vararg users: SubjectGroup)
}
package com.example.my20190520.Local.DAO

import  android.arch.persistence.room.Dao
import  android.arch.persistence.room.Query
import  android.arch.persistence.room.Insert
import  android.arch.persistence.room.Delete

import com.example.my20190520.Local.Entity.Group1

@Dao
interface GroupDao {
    @Query("SELECT * FROM group1")
    fun getAll(): List<Group1>

    @Insert
    fun insertAll(vararg users: Group1)


}
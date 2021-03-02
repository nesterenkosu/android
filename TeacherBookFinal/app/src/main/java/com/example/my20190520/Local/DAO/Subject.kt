package com.example.my20190520.Local.DAO

import  android.arch.persistence.room.Dao
import  android.arch.persistence.room.Query
import  android.arch.persistence.room.Insert
import  android.arch.persistence.room.Delete

import com.example.my20190520.Local.Entity.Subject

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject")
    fun getAll(): List<Subject>

    @Insert
    fun insertAll(vararg subjects: Subject)
    /*@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Subject>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Subject

    @Insert
    fun insertAll(vararg users: Subject)

    @Delete
    fun delete(user: Subject)*/
}
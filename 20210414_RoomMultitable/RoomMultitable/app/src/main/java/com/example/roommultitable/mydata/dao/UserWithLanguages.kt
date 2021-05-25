package com.example.roommultitable.mydata.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roommultitable.mydata.entity.UserWithLanguages

@Dao
interface UserWithLanguagesDao {
    @Transaction
    @Query("SELECT * FROM table_users")
    fun getUsersWithLanguages(): List<UserWithLanguages>
}
package com.example.roommultitable.mydata.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommultitable.mydata.entity.Language
import com.example.roommultitable.mydata.entity.UserLanguages

@Dao
interface UserLanguagesDao {
    //CRUD - Create Read Update Delete
    //Create
    @Insert
    fun insertAll(vararg user: UserLanguages)
}
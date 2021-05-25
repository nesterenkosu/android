package com.example.roommultitable.mydata.dao

import androidx.room.*
import com.example.roommultitable.mydata.entity.Language

@Dao
interface LanguageDao {
    //CRUD - Create Read Update Delete
    //Create
    @Insert
    fun insertAll(vararg user: Language)

    //Read

    //все языки
    @Query("SELECT * FROM table_languages")
    fun getAll():List<Language>

}
package com.example.roommultitable.mydata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roommultitable.mydata.entity.*
import com.example.roommultitable.mydata.dao.*

@Database(entities = [
    User::class,
    Language::class,
    UserLanguages::class
],version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun languageDao():LanguageDao
    abstract fun userlanguagesDao():UserLanguagesDao
    abstract fun userwithlanguagesDao():UserWithLanguagesDao
}
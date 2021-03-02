package com.example.my20190520.Local

import  android.arch.persistence.room.Database
import  android.arch.persistence.room.RoomDatabase

import com.example.my20190520.Local.DAO.GroupDao
import com.example.my20190520.Local.DAO.JournalDao
import com.example.my20190520.Local.DAO.LabDao
import com.example.my20190520.Local.DAO.StudentDao
import com.example.my20190520.Local.DAO.SubjectDao
import com.example.my20190520.Local.DAO.SubjectGroupDao
import com.example.my20190520.Local.DAO.UserDao
import com.example.my20190520.Local.Entity.*

@Database(entities = arrayOf(Group1::class,Journal::class,Lab::class,Student::class,Subject::class,SubjectGroup::class,User::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun journalDao(): JournalDao
    abstract fun labDao(): LabDao
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun subjectGroupDao(): SubjectGroupDao
    abstract fun userDao(): UserDao
}
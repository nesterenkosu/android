package com.example.roommultitable.mydata.dao

import androidx.room.*
import com.example.roommultitable.mydata.entity.*


@Dao
interface UserDao {
    //CRUD - Create Read Update Delete
    //Create
    @Insert
    fun insertAll(vararg user: User)

    //Read

    //все пользователи
    @Query("SELECT * FROM table_users")
    fun getAll():List<User>

    //пользователь с заданным id
    @Query("SELECT * FROM table_users WHERE UserID=:Id")
    fun getUserById(Id:Int):User

    //Update
    @Update
    fun update(user:User)

    //Delete
    @Delete
    fun delete(user:User)

}
package com.example.roommultitable.mydata.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.RESTRICT
import androidx.room.PrimaryKey


@Entity(tableName = "table_users")
data class User(
    @PrimaryKey(autoGenerate = true) var UserID: Int,
    @ColumnInfo(name="first_name") var Name: String,
    var Age: Int,
    var Email:String?
)


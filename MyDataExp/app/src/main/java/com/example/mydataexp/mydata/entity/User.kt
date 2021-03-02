package com.example.mydataexp.mydata

import  androidx.room.Entity
import  androidx.room.PrimaryKey
import  androidx.room.ColumnInfo

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
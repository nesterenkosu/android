package com.example.my20190520.Local.Entity

import  android.arch.persistence.room.Entity
import  android.arch.persistence.room.PrimaryKey
import  android.arch.persistence.room.ColumnInfo

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
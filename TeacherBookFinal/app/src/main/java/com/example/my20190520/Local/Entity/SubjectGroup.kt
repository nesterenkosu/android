package com.example.my20190520.Local.Entity

import  android.arch.persistence.room.Entity
import  android.arch.persistence.room.PrimaryKey
import  android.arch.persistence.room.ColumnInfo

@Entity
data class SubjectGroup(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "SubjectID") val SubjectID: Int,
    @ColumnInfo(name = "GroupID") val GroupID: Int
)
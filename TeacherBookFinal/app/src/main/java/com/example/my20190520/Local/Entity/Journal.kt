package com.example.my20190520.Local.Entity

import  android.arch.persistence.room.Entity
import  android.arch.persistence.room.PrimaryKey
import  android.arch.persistence.room.ColumnInfo

@Entity
data class Journal(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "StudentID") val StudentID: Int,
    @ColumnInfo(name = "LabID") val LabID: Int,
    @ColumnInfo(name = "NotSynchronized") val NotSynchronized: Int
)


class JournalView(
    val uid: Int,
    val Name: String?
)
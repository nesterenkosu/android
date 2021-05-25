package com.example.roommultitable.mydata.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_languages")
data class Language(
    @PrimaryKey(autoGenerate = true) var LanguageID: Int,
    @ColumnInfo(name="first_name") var Name: String
)
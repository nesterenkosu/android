package com.example.roommultitable.mydata.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


        //Класс-сущность. Данному классу соответствует таблица в базе данных
@Entity(tableName = "table_userlanguages",foreignKeys = [
    ForeignKey(
        entity = User::class, //Таблица, на которую ссылается внешний ключ
        childColumns = arrayOf("UserID"), //Поле, являющееся внешним ключом
        parentColumns = arrayOf("UserID"), //Поле, являющееся первичным ключом во внешней табилице
        onDelete = ForeignKey.RESTRICT //Способ поддержки ссылочной целостности
    ),
    ForeignKey(
        entity = Language::class, //Таблица, на которую ссылается внешний ключ
        childColumns = arrayOf("LanguageID"), //Поле, являющееся внешним ключом
        parentColumns = arrayOf("LanguageID"), //Поле, являющееся первичным ключом во внешней табилице
        onDelete = ForeignKey.RESTRICT //Способ поддержки ссылочной целостности
    )
])
data class UserLanguages(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    var UserID: Int,
    var LanguageID: Int
)
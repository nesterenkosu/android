package com.example.roommultitable.mydata.entity

import androidx.room.*

//Класс-представление (этому классу не соответсвует таблица в БД)
data class UserWithLanguages(
        @Embedded val user: User, //Свойства какого-то одного пользователя
        @Relation(
        parentColumn = "UserID",
        entityColumn = "LanguageID",
        associateBy = Junction(UserLanguages::class)
    )
        //Список языков программирования, которые знает данный данный пользователь
    val languages: List<Language>
)

/*data class UserWithLanguages(
        @PrimaryKey(autoGenerate = true) var uid: Int,
        @ColumnInfo(name="first_name") var Name: String
)*/
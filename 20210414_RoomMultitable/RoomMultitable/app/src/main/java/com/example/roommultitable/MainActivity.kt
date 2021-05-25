package com.example.roommultitable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.Room
import java.lang.Exception
import com.example.roommultitable.mydata.AppDatabase
import com.example.roommultitable.mydata.entity.Language
import com.example.roommultitable.mydata.entity.User
import com.example.roommultitable.mydata.entity.UserLanguages

class MainActivity : AppCompatActivity() {
    private lateinit var tv_test: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_test = findViewById(R.id.tv_test)

        var s = ""

        val db : AppDatabase

        try {
            db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "my_database_000000"
            ).allowMainThreadQueries().build()

            db.languageDao().insertAll(Language(0,"C++"))
            db.languageDao().insertAll(Language(0,"Pascal"))
            db.languageDao().insertAll(Language(0,"Basic"))
            db.languageDao().insertAll(Language(0,"Kotlin"))
            s+="Языки успешно добавлены\n"

            //Получение id последнего добавленного языка
            var languages_ids = arrayOf<Int>(-1,-1,-1,-1)
            var idx = 0
            db.languageDao().getAll().forEach {
                languages_ids[idx] = it.LanguageID
                idx++
            }

            //Создаём пользователя с языком программирования, добавленным выше
            db.userDao().insertAll(User(0,"FirstUser",20,"first@mail.ru"))
            db.userDao().insertAll(User(0,"SecondUser",20,"second@mail.ru"))
            s+="Пользователи успешно добавлены\n"
            var users_ids = arrayOf<Int>(-1,-1)
            idx=0
            db.userDao().getAll().forEach {
                users_ids[idx] = it.UserID
                idx++
            }

            db.userlanguagesDao().insertAll(UserLanguages(0,users_ids[0],languages_ids[0]))
            db.userlanguagesDao().insertAll(UserLanguages(0,users_ids[0],languages_ids[3]))

            db.userlanguagesDao().insertAll(UserLanguages(0,users_ids[1],languages_ids[2]))
            db.userlanguagesDao().insertAll(UserLanguages(0,users_ids[1],languages_ids[1]))

            s+="Пользователям назначены языки программирования\n"

            var res = db.userwithlanguagesDao().getUsersWithLanguages()

            res.forEach {
                s+="=======\nИмя пользователя "+it.user.Name+"\n"
                s+="Языки программирования:\n"
                it.languages.forEach {
                    s+=it.Name+"\n"
                }
            }
            //val k = 4
        }catch (ex: Exception) {
            s+="Ошибка "+ex.toString()
        }

        tv_test.setText(s)
    }
}
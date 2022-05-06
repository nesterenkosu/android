package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewdemo.mydata.entity.User
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var rv_users: RecyclerView
    private lateinit var tv_result: TextView
    private lateinit var rv_users_items: ArrayList<User>
    private lateinit var adapter: RecyclerViewAdapter_Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_users=findViewById(R.id.rv_users)
        tv_result=findViewById(R.id.tv_result)

        //задание в качестве компоновочного контейнера для
        //элементов списка компонента LinearLayoutManager
        //с вертикальным расположением элементов
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_users.layoutManager = linearLayoutManager

        //Создание массива элементов, через который в RecyclerView
        //будут поступать элементы списка
        rv_users_items = ArrayList<User>()
        //Создание экземпляра объекта RecyclerViewAdapter_Users,
        //определяющего логику работа элемента RecyclerView

        adapter = RecyclerViewAdapter_Users(
                rv_users_items,
                object : RecyclerViewOnClickListener {
                    //Обработчик щелчка на элементе списка
                    override fun onClicked(idx: Int) {
                        //Вывод выбранного элемента
                        tv_result.setText(
                                "Вы выбрали: "
                                        +rv_users_items[idx].Name+" "
                                        +rv_users_items[idx].Age+" "
                                        +rv_users_items[idx].Email
                        )
                    }
                }
        )

        //Вспомогательная переменная adapter служит для более короткого
        //вызова notifyDataSetChanged() в коде далее
        //(чтобы не писать (rv_users.adapter as RecyclerViewAdapter_Users).notifyDataSetChanged())
        rv_users.adapter=adapter

        //Тестируем работу RecyclerView

        //добавляем элементы в массив rv_users_items
        /*rv_users_items.add(User(1,"Vasya",30,"vasya@mail.ru"))
        rv_users_items.add(User(2,"Kolya",25,"kolya@mail.ru"))
        rv_users_items.add(User(3,"Petya",44,"kolya@mail.ru"))*/
        var i:Int
        for(i in 0..100) {
            rv_users_items.add(User(i,"Kolya",i,"kolya@mail.ru"))
        }
        //и сообщаем RecyclerView, что данные в массиве изменились
        //и необходимо выполнить перерисовку списка
        adapter.notifyDataSetChanged()

    }
}
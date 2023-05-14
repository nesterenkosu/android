package com.example.spinnersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.spinnersample.entity.SpinerItem


class MainActivity : AppCompatActivity() {
    private lateinit var btn_get: Button
    private lateinit var btn_set: Button
    private lateinit var btn_set_position: Button
    private lateinit var tv_result: TextView
    //
    private lateinit var myspinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_get = findViewById(R.id.btn_get)
        btn_set = findViewById(R.id.btn_set)
        btn_set_position = findViewById(R.id.btn_set_position)
        tv_result = findViewById(R.id.tv_result)

        //связывание виджета на форме с переменной
        myspinner = findViewById(R.id.spn_myspinner)

        //Создание набора элементов, отображаемых в выпадающем списке
        var spinner_items = ArrayList<SpinerItem>()
        //первый элемент - приглашение выбрать язык программирования
        spinner_items.add(SpinerItem(-1,"--Выберите элемент из этого списка"))
        //добавление остальных элементов
        spinner_items.add(SpinerItem(20,"Пункт раз"))
        spinner_items.add(SpinerItem(33,"Пункт два"))
        spinner_items.add(SpinerItem(52,"Пункт три"))

        //На основе созданного набора элементов создаётся
        //так называемый адаптер данных - вспомогательный
        //объект, определяющий способ отображения
        //этих элементов в выпадающем списке
        val dataAdapter = object: ArrayAdapter<SpinerItem>(this,
            android.R.layout.simple_spinner_dropdown_item,spinner_items) {
            override fun isEnabled(position: Int): Boolean {
                //Делаем первый пункт (строку "Выберите язык программирования") неактивным
                return position != 0
            }
        }

        //Связывание созданного адаптера данных с выпадающим списком
        myspinner.setAdapter(dataAdapter)

        btn_get.setOnClickListener {
            //Получение информации об элементе, выбранном из списка
            var selected_item = myspinner.adapter.getItem(myspinner.selectedItemPosition) as SpinerItem
            tv_result.setText(
                "Вы выбрали: "+selected_item.Name+"\n"+
                "uid="+selected_item.uid.toString()+"\n"+
                "Name="+selected_item.Name+"\n"+
                "Порядковый номер в списке: "+myspinner.selectedItemPosition.toString()
            )
        }

        btn_set.setOnClickListener {
            //Программная активация элемента списка
            //по значению поля данных
            var selected_item_uid = 33

            for (position in 0 until dataAdapter.getCount()) {
                if ((myspinner.getItemAtPosition(position) as SpinerItem).uid
                    == selected_item_uid) {
                    //собственно активация
                    myspinner.setSelection(position)
                    break
                }
            }
        }

        btn_set_position.setOnClickListener {
            //Программная активация элемента списка
            //по порядковому номеру в списке
            var selected_item_position = 3

            myspinner.setSelection(selected_item_position)
        }
    }
}
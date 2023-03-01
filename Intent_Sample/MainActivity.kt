package com.example.intentsample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var et_welcome: Button
    private lateinit var btn_activity1: Button
    private lateinit var btn_activity2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_activity1=findViewById(R.id.btn_activity1)
        btn_activity2=findViewById(R.id.btn_activity2)


        btn_activity1.setOnClickListener {
            //Создание объекта интента
            val intent_activity1 =  Intent(this,MainActivity2::class.java)
            //Передача данных в открываемое окно
            intent_activity1.putExtra("my_val","Hello")
            //Собственно открытие окна
            startActivityForResult(intent_activity1,1)
        }

        btn_activity2.setOnClickListener {
            val webpage: Uri = Uri.parse("https://inf.susu.ac.ru/")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            /*val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:0,0?q=34.99,-106.61(Treasure)")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }*/
            /*val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+7921300123")
            }

            //Проверка, установлено ли на устройстве приложение
            //с помошью которого можно выполнить желаемое действие (позвонить)
            if (intent.resolveActivity(packageManager) != null) {
                //если приложение установлено - открываем его окно
                startActivity(intent)
            }*/
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) { //MainActivity2
            if(resultCode == Activity.RESULT_OK) {
                //Получение данных из дочернего окна
                var my_val = data!!.getStringExtra("my_val")
                //Вывод полученной строки на кнопку
                btn_activity1.setText(my_val)
            }
        }
    }
}
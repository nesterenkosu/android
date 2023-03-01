package com.example.intentsample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    private lateinit var et_val: EditText
    private lateinit var btn_go: Button
    private lateinit var btn_cancel: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        et_val = findViewById(R.id.et_val)
        btn_go = findViewById(R.id.btn_go)
        btn_cancel = findViewById(R.id.btn_cancel)

        //Принятие данных из родительского окна
        val my_val = intent.getStringExtra("my_val")
        et_val.setText(my_val)

        btn_go.setOnClickListener {
            //Возврат данных в родительское окно
            //создание объекта Intent
            var result_intent = Intent()

            //Передача данных через переменную my_val
            result_intent.putExtra("my_val",et_val.text.toString())

            //собственно возврат в родительское окно
            setResult(Activity.RESULT_OK,result_intent)
            //закрытие текущего окна
            finish()
        }

        btn_cancel.setOnClickListener {
            //Возврат данных в родительское окно
            //создание объекта Intent
            var result_intent = Intent()

            //собственно возврат в родительское окно
            setResult(Activity.RESULT_CANCELED,result_intent)
            //закрытие текущего окна
            finish()
        }
    }
}
package com.example.myimplicitintentdemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btn_call: Button
    private lateinit var btn_map: Button
    private lateinit var btn_web: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_call = findViewById(R.id.btn_call)
        btn_map = findViewById(R.id.btn_map)
        btn_web = findViewById(R.id.btn_web)

        btn_call.setOnClickListener {

            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+7921300123")
            }

            //Проверка, установлено ли на устройстве приложение
            //с помошью которого можно выполнить желаемое действие (позвонить)
            if (intent.resolveActivity(packageManager) != null) {
                //если приложение установлено - открываем его окно
                startActivity(intent)
            }
        }

        btn_map.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:0,0?q=34.99,-106.61(Treasure)")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        btn_web.setOnClickListener {

            val webpage: Uri = Uri.parse("https://digital-economy.susu.ru/")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}

package com.example.personalgreeter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var ed_username: EditText
    private lateinit var btn_go: Button
    private lateinit var tv_greeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ed_username = findViewById(R.id.ed_username);
        btn_go = findViewById(R.id.btn_go)
        tv_greeting = findViewById(R.id.tv_greeting)

        btn_go.setOnClickListener {
            tv_greeting.setText(
                    "Здравствуй, "+ed_username.getText()+"!"
            )
        }
    }
}
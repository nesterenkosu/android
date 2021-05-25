package com.example.myparallelcounters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    //Канал (объявление глобальной переменной)
    private lateinit var channel: Channel<Int>

    private lateinit var btn_run_counter: Button
    private lateinit var btn_dotask: Button
    private lateinit var tv_sender: TextView
    private lateinit var tv_receiver: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Собственно создание канала
        channel = Channel<Int>()

        btn_run_counter = findViewById(R.id.btn_run_counter)

        tv_sender = findViewById(R.id.tv_sender)
        tv_receiver = findViewById(R.id.tv_receiver)

        btn_run_counter.setOnClickListener {
            //Запуск обеих корутин
            sender();
            //receiver();
        }

    }

    fun sender() = launch{
        //Создание "актора" - некоторой именованной корутины
        val actr = actor<Int>() {
            //Актор в данном случае получатель данных
            for (data_item in channel)
                tv_receiver.setText("Получено: "+data_item.toString())
        }

        var v = 0
        var tmp = 0
        while(true) {
            //Увеличение переменной-счётчика на 1
            v= withContext(Dispatchers.Default) {
                Thread.sleep(1000)
                tmp++
            }

            //Отправка данных (значения переменной-счётчика) в канал
            actr.send(v)

            tv_sender.setText("Отправлено: "+v.toString())
        }
    }

    /*fun receiver() = launch{

        /*while(true) {
            //Принятие данных из канала
            tv_receiver.setText("Принято:"+channel.receive().toString())
        }*/

        //Альтернативный синтаксис принятия данных из канала
        for(data_item in channel)
            tv_receiver.setText("Принято:"+data_item.toString())
    }*/
}
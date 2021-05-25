package com.example.myparallelcounters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    //Активация поддержки корутин
    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    private lateinit var btn_run_counter: Button
    private lateinit var btn_dotask: Button
    private lateinit var tv_counter: TextView
    private lateinit var tv_task: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Получение доступа к элементам управления
        //объявленным на форме
        btn_run_counter = findViewById(R.id.btn_run_counter)
        btn_dotask = findViewById(R.id.btn_dotask)
        tv_counter = findViewById(R.id.tv_counter)
        tv_task = findViewById(R.id.tv_task)

        btn_run_counter.setOnClickListener {
            //Запуск первого счётчика
            runCounter()
            btn_run_counter.setText("Запустили")
        }

        btn_dotask.setOnClickListener {
            //Запуск второго счётчика
            runCounter2()
            btn_dotask.setText("Запустили")
        }
    }

    //Каждый счётчик директивой launch запускакется в новом потоке
    //пользовательского интерфейса (Dispatchers.Main)
    fun runCounter() = launch{
        tv_counter.setText("Счётчик запущен")
        var v = 0
        var tmp = 0
        while(true) {
            //Выполнить действия в отдельном потоке и получить
            //результат в текущем потоке
            v= withContext(Dispatchers.Default) {
                //Приостановить поток на ОДНУ СЕКУНДУ
                Thread.sleep(1000)
                //увеличить значение счётчика на 1
                //вернуть полученное значение в родительский поток
                tmp++
            }

            //Вывести новое значение счётчика в текстовом поле
            tv_counter.setText(v.toString())
        }
    }

    //Каждый счётчик директивой launch запускакется в новом потоке
    //пользовательского интерфейса (Dispatchers.Main)
    fun runCounter2() = launch{
        tv_task.setText("Счётчик запущен")
        var v = 0
        var tmp = 0
        while(true) {
            //Выполнить действия в отдельном потоке и получить
            //результат в текущем потоке
            v= withContext(Dispatchers.Default) {
                //Приостановить поток на ПОЛСЕКУНДЫ
                Thread.sleep(500)
                //увеличить значение счётчика на 1
                //вернуть полученное значение в родительский поток
                tmp++
            }

            //Вывести новое значение счётчика в текстовом поле
            tv_task.setText(v.toString())
        }
    }
}
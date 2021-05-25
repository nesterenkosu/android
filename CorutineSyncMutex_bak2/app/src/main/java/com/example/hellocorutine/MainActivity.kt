package com.example.hellocorutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    //Задание параметров многопоточности
    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    private lateinit var btn_start_timer: Button
    private lateinit var btn_dosomething:Button
    private lateinit var tv_seconds:TextView
    private lateinit var tv_taskoutput:TextView

    //Мьютекс
    private lateinit var mtx: Mutex

    var s = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_timer=findViewById(R.id.btn_start_timer)
        tv_taskoutput=findViewById(R.id.tv_taskoutput)

        supportActionBar!!.title = "Мьютексы"
        //Мьютекс
        mtx = Mutex()

        btn_start_timer.setOnClickListener {
            //ПАРАЛЛЕЛЬНЫЙ запуск функций func1 и func2
            func1()
            func2()

            tv_taskoutput.setText("Выполнение параллельных функций. Подождите...")

            launch {
                withContext(Dispatchers.IO) {
                    Thread.sleep(5000)
                }
                tv_taskoutput.setText(s)
            }
        }
    }

    public fun func1() = launch {
        s+="Функция func1(): несинхронизированный шаг1\n"
        s+="Функция func1(): несинхронизированный шаг2\n"
        mtx.withLock {
            s += "Функция func1(): внутри мьютекса шаг 1\n";
            //Временная задержка 3 сек
            delay(3000)
            s += "Функция func1(): внутри мьютекса шаг 2\n";
        }
    }

    public fun func2() = launch {
        s+="Функция func2(): несинхронизированный шаг1\n"
        s+="Функция func2(): несинхронизированный шаг2\n"
        s+="Функция func2(): несинхронизированный шаг3\n"
        mtx.withLock {
            s += "Функция func2(): внутри мьютекса шаг 1\n";
            s += "Функция func2(): внутри мьютекса шаг 2\n";
        }
    }

    //Функция, выполняемая в отдельном потоке (корутине)
    public fun runTimer() = launch {
        var sec = 0
        var tmp=0
        while(true) {
            tmp=withContext(Dispatchers.IO) {
                Thread.sleep(1000)
                sec++
            }

            drawSecondsToUi(tmp)
        }
    }

    //Функция, вызываемая из корутины, должна быть
    //объявлена с модификатором suspend
    public suspend fun drawSecondsToUi(seconds: Int){
        tv_seconds.setText(seconds.toString())
    }
}
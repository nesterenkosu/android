package com.example.hellocorutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.w3c.dom.Text
import java.util.concurrent.Semaphore
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    //Задание параметров многопоточности
    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    private lateinit var btn_1: Button
    private lateinit var btn_2:Button
    private lateinit var btn_3:Button
    private lateinit var tv_1:TextView
    private lateinit var tv_2:TextView
    private lateinit var tv_3:TextView

    //Семафор
    private lateinit var sem: Semaphore

    var s = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_1=findViewById(R.id.btn_1)
        btn_2=findViewById(R.id.btn_2)
        btn_3=findViewById(R.id.btn_3)
        tv_1=findViewById(R.id.tv_1)
        tv_2=findViewById(R.id.tv_2)
        tv_3=findViewById(R.id.tv_3)


        //Создание СЕМАФОРА

        //параметр permits задаёт количество
        //потоков, одновременно использующих
        //блокируемый ресурс

        //другими словами - количество блокировок
        //этого семафора в других параллельных потоках
        //необходимое для приостановки текущего
        //потока при вызове sem.acquire()
        sem = Semaphore(2)

        btn_1.setOnClickListener {
            //Запуск нового параллельного экземпляра функции func1
            func1(tv_1)
        }

        btn_2.setOnClickListener {
            //Запуск нового параллельного экземпляра функции func1
            func1(tv_2)
        }

        btn_3.setOnClickListener {
            //Запуск нового параллельного экземпляра функции func1
            func1(tv_3)
        }

    }

    public fun func1(tv:TextView) = launch {

        writelog(tv,"- Проверка наличия блокировки. В случае её наличия - ожидание её снятия.\n")
        withContext(Dispatchers.IO) {
            //Если другой поток уже заблокировал семафор -
            //текущий поток в sem.acquire() зависнет до снятия блокировки.
            //Далее текущий поток сам заблокирует данный семафор.

            //Если семафор ещё не был заблокирован другим потоком -
            //текущий поток сразу заблокирует данный семафор сам.
            sem.acquire()
        }
        writelog(tv,"- Дождались и установили собственную блокировку.\n")
        writelog(tv,"- Выполняем длительную и ресурсоёмкую операцию...\n")

        //Имитация длительной и ресурсоёмкой операции
        withContext(Dispatchers.IO) {delay(20000)}

        writelog(tv,"- Операция выполнена. Снимаем блокировку.\n")
        //Снятие блокировки семафора
        sem.release()
        writelog(tv,"- Блокировка снята.\n")
    }

    public suspend fun writelog(tv:TextView,s: String) {
        //Запись логов в текстовое поле на форме
        tv.setText(tv.getText().toString()+s)
    }
}
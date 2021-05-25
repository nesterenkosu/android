package com.example.myxmlrpcclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.*

import org.apache.xmlrpc.client.XmlRpcClient
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory
import org.apache.xmlrpc.client.XmlRpcSunHttpTransportFactory
import org.apache.xmlrpc.common.XmlRpcController
import java.net.URL

import kotlin.coroutines.CoroutineContext

import com.example.myxmlrpcclient.xmlrpc_mapping.Person


class MainActivity : AppCompatActivity(), CoroutineScope {
    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    private lateinit var tv_test: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_test=findViewById(R.id.tv_test)

        //tv_test.setText("All is good")
        WorkWithXmlRpc()

    }

    public fun WorkWithXmlRpc() = launch {
        try {
            val config = XmlRpcClientConfigImpl()

            //http://10.0.2.2:8888/xmlrpc.api.php
            config.serverURL = URL("http://10.0.2.2:8888/xmlrpc.api.php")


            val client = XmlRpcClient()


            client.setConfig(config)

            var s=""

            var person = HashMap<String,Any>()
            person.set("ID",0)
            person.set("Name","Androsha")
            person.set("Age",10)
            person.set("Mail","a@mail.ru")
            person.set("LanguageID",8)

            withContext(Dispatchers.IO) {
                client.execute("myservice:CreatePerson", arrayOf<Any>(person))
            }

            val params = arrayOf<Any>(2)
            val result = withContext(Dispatchers.IO) {
                client.execute("myservice:ReadPerson", params) as HashMap<String,String>
            }

            s+=result.get("Name");

            s+="\n----------------\n"
            withContext(Dispatchers.IO) {
                client.execute("myservice:ListPeople",arrayOf<Any>()) as Array<Object>
            }.forEach {
                s+=(it as HashMap<String,String>).get("Name")+" "+(it as HashMap<String,Int>).get("Age")+"\n"
            }

            /*s+="\n----------------\n"
            withContext(Dispatchers.IO) {
                client.execute("myservice:ListPeople",arrayOf<Any>()) as Array<Person>
            }.forEach {
                s+=it.Name +" "+it.Age+"\n"
            }*/

            tv_test.setText(s)

        } catch (ex:Exception) {
            tv_test.setText("Ошибка: "+ex.toString())
        }
    }
}
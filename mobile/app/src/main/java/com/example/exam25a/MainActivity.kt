package com.example.exam25a

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.exam25a.databinding.ActivityMainBinding
import com.example.exam25a.utils.logd
import com.example.exam25a.websocket.MyWebSocketListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        val request = Request.Builder().url("ws://10.0.2.2:2018").build()
        val listener = MyWebSocketListener()
        client.newWebSocket(request, listener)
        client.dispatcher.executorService.shutdown()

        val buttonOpenActivity1 = findViewById<Button>(R.id.button1)
        buttonOpenActivity1.setOnClickListener {
            val intent = Intent(this@MainActivity, Section1::class.java)
            startActivity(intent)
        }

        val buttonOpenActivity2 = findViewById<Button>(R.id.button2)
        buttonOpenActivity2.setOnClickListener {
            val intent = Intent(this@MainActivity, Section2::class.java)
            startActivity(intent)
        }

        val buttonOpenActivity3 = findViewById<Button>(R.id.button3)
        buttonOpenActivity3.setOnClickListener {
            val intent = Intent(this@MainActivity, Section3::class.java)
            startActivity(intent)
        }

//        val buttonAdd = findViewById<Button>(R.id.button2)
//        buttonAdd.setOnClickListener {
//            logd("Works")
//            GlobalScope.launch(Dispatchers.Main) {
//                val intent = Intent(this@MainActivity, EntityActivity::class.java)
//                startActivity(intent)
//            }
//        }
    }
}
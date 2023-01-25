package com.example.exam25a.websocket

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exam25a.MainActivity
import com.example.exam25a.utils.logd
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.security.AccessController.getContext

class MyWebSocketListener(): WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Hello, WebSocket!")
        logd("Connection established")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        logd("Received: $text")
//        val context = activity.applicationContext
//        Toast.makeText(context, "TTTTTTTTTTTTTTTTTTT",Toast.LENGTH_SHORT).show()
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        t.printStackTrace()
    }
}
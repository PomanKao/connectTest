package com.example.connectapplication.socket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.lifecycle.lifecycleScope
import com.example.connectapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

class ConnectSocketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_socket)

        startService(Intent(this, SocketServer::class.java))



        lifecycleScope.launch {
            launch(Dispatchers.IO) {
                Thread.sleep(2000)
                connectSocketServer()
            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this, SocketServer::class.java))
        super.onDestroy()
    }

    private fun connectSocketServer() {
        var read: BufferedReader? = null
        var writer: PrintWriter? = null
        var socket: Socket? = null
        try {
            if (socket == null) {
                socket = Socket("localhost", 9999)
            }
            while (!isFinishing) {
                if (writer == null) {
                    writer = PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
                }
                writer.println("客戶端發出訊息")

                if (read == null) {
                    read = BufferedReader(InputStreamReader(socket.getInputStream()))
                }
                val msg = read.readLine()
                println("客戶端接收訊息： $msg")
                SystemClock.sleep(1000)
            }
            println("客戶端關閉")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            read?.close()
            writer?.close()
            socket?.close()
        }

    }
}
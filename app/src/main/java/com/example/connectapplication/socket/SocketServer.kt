package com.example.connectapplication.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.text.TextUtils
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket

class SocketServer : Service() {

    var serverAllowed: Boolean = true

    private var runnable = Runnable {
        val serverSocket: ServerSocket = ServerSocket(9999)
        val accept: Socket = serverSocket.accept()
        Thread { response(accept) }.start()
    }

    override fun onCreate() {
        super.onCreate()
        Thread(runnable).start()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun response(accept: Socket) {
        try {
            val bufferedReaderIn: BufferedReader = BufferedReader(InputStreamReader(accept.getInputStream()))
            val out: PrintWriter = PrintWriter(BufferedWriter(OutputStreamWriter(accept.getOutputStream())), true)
            while (serverAllowed) {
                val msg = bufferedReaderIn.readLine()
                if (TextUtils.isEmpty(msg)) {
                    println("收到客戶端的訊息為空，切斷連線")
                    break
                }
                println("收到客戶端訊息: $msg")
                val msgOp = "加工從客戶端的訊息: $msg"
                out.println(msgOp)
            }
            println("關閉服務")
            bufferedReaderIn.close()
            out.close()
            accept.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        serverAllowed = true
        super.onDestroy()
    }
}
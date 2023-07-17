package com.example.connectapplication.http

import android.util.Log
import com.example.connectapplication.ApiService
import com.example.connectapplication.Config
import com.example.connectapplication.model.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpUrlConnectionTest : ApiService {

    companion object {
        private val TAG = HttpUrlConnectionTest::class.java.simpleName
    }

    override suspend fun getPosts(): List<Post> {
        var result: String = ""
        withContext(Dispatchers.IO) {

            val connection: HttpURLConnection
            try {
                val apiUrl = Config.HOST + "/posts"
                val url = URL(apiUrl)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.doInput = true
                val inputStream = connection.inputStream
                val status = connection.responseCode
                Log.d(TAG, "code $status")
                if (inputStream != null) {
                    val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
                    val bufferedReader = BufferedReader(inputStreamReader)
                    var line: String? = bufferedReader.readLine()
                    while (line != null) {
                        result += (line + "\n")
                        line = bufferedReader.readLine()
                    }
                } else {
                    result = "Error $status"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val listType =  object : TypeToken<List<Post>>(){}
        return Gson().fromJson(result, listType.type)
    }
}
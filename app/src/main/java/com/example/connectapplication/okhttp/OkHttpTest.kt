package com.example.connectapplication.okhttp

import android.util.Log
import com.example.connectapplication.ApiService
import com.example.connectapplication.Config
import com.example.connectapplication.model.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class OkHttpTest : ApiService {

    companion object {
        private val TAG = OkHttpTest::class.java.simpleName
    }

    override suspend fun getPosts(): List<Post> {
        var result: String?
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val url = Config.HOST + "/posts"
            val request = Request.Builder()
                .url(url)
                .get()
                .build()
            val response = client.newCall(request).execute()
            result = response.body?.string()
        }
        val listType =  object : TypeToken<List<Post>>(){}
        return Gson().fromJson(result, listType.type)
    }

}
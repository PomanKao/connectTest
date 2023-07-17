package com.example.connectapplication.retrofit

import com.example.connectapplication.ApiService
import com.example.connectapplication.Config
import com.example.connectapplication.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest : ApiService {

    override suspend fun getPosts(): List<Post>? {
        var result: Response<List<Post>>
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val retrofit = Retrofit.Builder()
                .baseUrl(Config.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(PostService::class.java)
            val response = service.getPosts().execute()
            result = response
        }
        return result.body()
    }
}
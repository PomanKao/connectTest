package com.example.connectapplication.retrofit

import com.example.connectapplication.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}
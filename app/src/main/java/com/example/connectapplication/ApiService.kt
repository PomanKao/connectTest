package com.example.connectapplication

import com.example.connectapplication.model.Post

interface ApiService {

    suspend fun getPosts(): List<Post>?

}
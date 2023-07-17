package com.example.connectapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectapplication.ResResult
import com.example.connectapplication.http.HttpUrlConnectionTest
import com.example.connectapplication.okhttp.OkHttpTest
import com.example.connectapplication.retrofit.RetrofitTest
import com.example.connectapplication.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConnectViewModel: ViewModel() {

    fun httpGetPosts() {
        viewModelScope.launch {
            when ( val postResult = safeApiCall { RetrofitTest().getPosts() }) {
                is ResResult.Success -> {
                    Log.d("Poman", "size ${postResult.data?.size}")
                    Log.d("Poman", "body ${postResult.data?.get(0)?.body}")
                }
                is ResResult.Fail -> {
                    Log.d("Poman", "message: ${postResult.message}")
                    Log.d("Poman", "e: ${postResult.throwable}")
                }
            }
        }
    }

}
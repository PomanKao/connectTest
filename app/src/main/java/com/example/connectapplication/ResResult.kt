package com.example.connectapplication

sealed class ResResult<T> {
    data class Success<T>(val data: T): ResResult<T>()
    data class Fail<T>(
        val message: String? = null,
        val throwable: Throwable? = null
    ): ResResult<T>()
}
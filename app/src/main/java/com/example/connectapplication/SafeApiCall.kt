package com.example.connectapplication

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResResult<T> {
    return try {
        ResResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResResult.Fail("${throwable.message} IOException : Network error !!",throwable)
            is HttpException -> ResResult.Fail(throwable.message ?: "",throwable)
            else -> ResResult.Fail(throwable.message,throwable)
        }
    }
}
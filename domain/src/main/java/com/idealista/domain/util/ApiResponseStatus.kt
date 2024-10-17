package com.idealista.domain.util

sealed class ApiResponseStatus<out T> {
    class Success<T>(val data: T) : ApiResponseStatus<T>()
    class Error(val error: Exception) : ApiResponseStatus<Nothing>()
}
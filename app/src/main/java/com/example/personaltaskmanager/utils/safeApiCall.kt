package com.example.personaltaskmanager.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend inline fun <T> safeApiCall(
    crossinline body: suspend () -> Response<T>
): Resource<T> {
    return try {
        // blocking block
        val response = withContext(Dispatchers.IO) {
            body()
        }
        return  if (response.isSuccessful){
            Resource.Success(response.body()!!)
        }else {
            Resource.Error(response.code() , response.errorBody().toString())
        }

    } catch (e: Exception) {
        Resource.Error(-1 , e.message?:"unknown error")
    }
}
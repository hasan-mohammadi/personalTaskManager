package com.example.personaltaskmanager.utils


sealed class Resource<out R>() {


    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val errorCode: Int,
        val errorMessage: String
    ) : Resource<Nothing>()

     class Loading : Resource<Nothing>()


}
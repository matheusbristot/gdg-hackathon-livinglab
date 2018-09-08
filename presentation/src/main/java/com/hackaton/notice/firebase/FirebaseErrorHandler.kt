package com.hackaton.notice.firebase

interface FirebaseErrorHandler {

    fun getMessageError(exception: Exception): String
}
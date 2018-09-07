package com.shittyapp.domain.entities

sealed class FieldErrorType : Exception() {
    object InvalidEmail : FieldErrorType()
    object InvalidPassword : FieldErrorType()
    object PasswordTooShort : FieldErrorType()
}
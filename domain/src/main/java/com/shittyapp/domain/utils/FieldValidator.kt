package com.shittyapp.domain.utils

import com.shittyapp.domain.entities.FieldErrorType

class FieldValidator {
    companion object {
        private const val EMAIL_REGEX = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
        private const val MIN_PASSWORD_LENGTH = 4

        fun validateEmail(email: String): FieldErrorType? {
            return when {
                !email.matches(Regex(EMAIL_REGEX)) -> FieldErrorType.InvalidEmail
                else -> null
            }
        }

        fun validatePassword(password: String): FieldErrorType? {
            return when {
                password.isBlank() -> FieldErrorType.InvalidPassword
                password.length < MIN_PASSWORD_LENGTH -> FieldErrorType.PasswordTooShort
                else -> null
            }
        }
    }
}
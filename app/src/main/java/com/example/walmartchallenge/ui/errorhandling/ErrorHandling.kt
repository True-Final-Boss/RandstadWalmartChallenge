package com.example.walmartchallenge.ui.errorhandling

import com.example.walmartchallenge.data.errorhandling.Result

object ErrorHandling {

    inline fun <reified T> Result<T>.doIfFailure(callback: (error: String?, throwable: Throwable?) -> Unit) {
        if (this is Result.Failure) {
            callback(message, throwable)
        }
    }

    inline fun <reified T> Result<T>.doIfSuccess(callback: (value: T) -> Unit) {
        if (this is Result.Success) {
            callback(value)
        }
    }
}
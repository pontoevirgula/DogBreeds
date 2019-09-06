package com.chsltutorials.dogbreeds.core.util


sealed class Result<out T : Any?>

data class Success<out T : Any>(val data: T) : Result<T>()
data class Empty<out T : Any>(val data: T) : Result<T>()
data class Loading(val loading : String?) : Result<Nothing>()
data class Failure(val error: Throwable?) : Result<Nothing>()
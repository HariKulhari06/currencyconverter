package com.example.asssignmentsdktesttask.domain.model

sealed class AppError : RuntimeException {
    constructor()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)

    sealed class ApiException(cause: Throwable?) : AppError(cause) {
        class NetworkException(cause: Throwable?) : ApiException(cause)
        class ServerException(cause: Throwable?) : ApiException(cause)
        class SessionNotFoundException(cause: Throwable?) : AppError(cause)
        class UnknownException(cause: Throwable?) : AppError(cause)
        class MaxRequestReachedException(cause: Throwable?) : AppError(cause)
        class NetworkConnectivityError(cause: Throwable?) : AppError(cause)
        class ApiErrorException(cause: Throwable?) : AppError(cause)
    }


    class UnknownException(cause: Throwable?) : AppError(cause)
}
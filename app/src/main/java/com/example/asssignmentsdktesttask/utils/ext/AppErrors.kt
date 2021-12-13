package com.example.asssignmentsdktesttask.utils.ext

import androidx.annotation.StringRes
import com.example.asssignmentsdktesttask.R
import com.example.asssignmentsdktesttask.data.network.exception.MaxRequestReachedException
import com.example.asssignmentsdktesttask.data.network.exception.NetworkConnectivityException
import com.example.asssignmentsdktesttask.domain.model.AppError

/**
 * Convert Throwable to AppError
 */
fun Throwable?.toAppError(): AppError? {
    this?.printStackTrace()
    return when (this) {
        null -> null
        is AppError -> this
        is NetworkConnectivityException -> AppError.ApiException.NetworkException(this)
        is MaxRequestReachedException -> AppError.ApiException.MaxRequestReachedException(this)
        else -> AppError.UnknownException(this)
    }
}

/**
 * Convert AppError to String Resources
 */
@StringRes
fun AppError.stringRes() = when (this) {
    is AppError.ApiException.NetworkException -> R.string.error_no_internet_connection
    is AppError.ApiException.MaxRequestReachedException -> R.string.the_maximum_allowed_api_amount_of_monthly_api_requests_has_been_reached
    is AppError.ApiException.ServerException -> R.string.error_server
    is AppError.ApiException.SessionNotFoundException -> R.string.error_unknown
    is AppError.ApiException.UnknownException -> R.string.error_unknown
    is AppError.UnknownException -> R.string.error_unknown
    else -> R.string.error_unknown
}

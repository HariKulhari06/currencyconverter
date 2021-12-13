package com.example.asssignmentsdktesttask.data.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.example.asssignmentsdktesttask.data.network.exception.NetworkConnectivityException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityInterceptor @Inject constructor(
    @ApplicationContext val context: Context,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isConnected()) {
            throw NetworkConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()

        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}
